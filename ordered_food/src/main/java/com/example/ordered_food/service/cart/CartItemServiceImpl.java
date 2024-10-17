package com.example.ordered_food.service.cart;


import com.example.ordered_food.exception.CartItemException;
import com.example.ordered_food.exception.UserException;
import com.example.ordered_food.model.Cart;
import com.example.ordered_food.model.CartIem;
import com.example.ordered_food.model.Product;
import com.example.ordered_food.model.User;
import com.example.ordered_food.repository.CartItemRepository;
import com.example.ordered_food.repository.CartRepository;
import com.example.ordered_food.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService{

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public CartIem createCartItem(CartIem cartIem) {
        cartIem.setQuantity(1);
        cartIem.setPrice(cartIem.getProduct().getPrice()* cartIem.getQuantity());
        cartIem.setDiscountedPrice(cartIem.getProduct().getDiscountedPrice()*cartIem.getQuantity());


        CartIem createCartItem = cartItemRepository.save(cartIem);
        return createCartItem;
    }

    @Override
    public CartIem updateCartItem(Long userId, Long id, CartIem cartIem) throws CartItemException, UserException {
        CartIem item = findCartItemById(id);
        User user = userService.findUserById(userId);


        if(user.getId().equals(userId)){
            item.setQuantity(cartIem.getQuantity());
            item.setPrice(cartIem.getProduct().getPrice() * cartIem.getQuantity());
            item.setDiscountedPrice(item.getProduct().getDiscountedPrice() * item.getQuantity());
        }
        return  cartItemRepository.save(item);
    }

    @Override
    public CartIem isCartItemExist(Cart cart, Product product, Long userId) {
        CartIem cartItem = cartItemRepository.isCartITemExist(cart,product,userId);

        return cartItem;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        CartIem cartItem = findCartItemById(cartItemId);
        User user = userService.findUserById(cartItem.getUserId());

        User reqUser = userService.findUserById(userId);

        if(user.getId().equals(reqUser.getId())){
            cartItemRepository.deleteById(cartItemId);
        }else {
            throw  new UserException("you can't remove another users item");

        }

    }

    @Override
    public CartIem findCartItemById(Long CartItemId) throws CartItemException {
        Optional<CartIem> opt = cartItemRepository.findById(CartItemId);

        if(opt.isPresent()){
            return  opt.get();
        }else {
            throw new CartItemException("cartItem not found with id "+ CartItemId);
        }
    }
}
