package com.example.ordered_food.service.cart;

import com.example.ordered_food.exception.ProductException;
import com.example.ordered_food.model.Cart;
import com.example.ordered_food.model.CartIem;
import com.example.ordered_food.model.Product;
import com.example.ordered_food.model.User;
import com.example.ordered_food.repository.CartRepository;
import com.example.ordered_food.request.AddItemRequest;
import com.example.ordered_food.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{


    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private  CartItemService cartItemService;

    @Autowired
    private ProductService productService;
    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
        Cart cart = cartRepository.findUserById(userId);
        Product product = productService.findProductById(req.getProductId());


        CartIem isPresent = cartItemService.isCartItemExist(cart,product,userId);

        if(isPresent == null){
            CartIem cartItem = new CartIem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(req.getQuantity());
            cartItem.setUserId(userId);


            int price = req.getQuantity()* product.getDiscountedPrice();
            cartItem.setPrice(price);


            CartIem createdCartItem =cartItemService.createCartItem(cartItem);

            cart.getCartIems().add(createdCartItem);
        }

        return "Item add to Cart";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart = cartRepository.findUserById(userId);

        int totalPrice = 0 ;
        int totalDiscountedPrice = 0 ;
        int totalItem = 0 ;

        for (CartIem cartItem : cart.getCartIems()){
            totalPrice += cartItem.getPrice();
            totalDiscountedPrice += cartItem.getDiscountedPrice();
            totalItem += cartItem.getQuantity();
        }
        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setTotalPrice(totalPrice);
        cart.setTotalItem(totalItem);
        cart.setDiscounted(totalPrice - totalDiscountedPrice);
        return cartRepository.save(cart);
    }
}
