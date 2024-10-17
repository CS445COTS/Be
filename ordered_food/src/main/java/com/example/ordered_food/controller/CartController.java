package com.example.ordered_food.controller;


import com.example.ordered_food.exception.ProductException;
import com.example.ordered_food.exception.UserException;
import com.example.ordered_food.model.Cart;
import com.example.ordered_food.model.User;
import com.example.ordered_food.request.AddItemRequest;
import com.example.ordered_food.response.ApiResponse;
import com.example.ordered_food.service.cart.CartService;
import com.example.ordered_food.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization")String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        Cart cart = cartService.findUserCart(user.getId());
        return  new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<ApiResponse> addItemToCard
            (@RequestBody AddItemRequest req,
             @RequestHeader("Authorization")String jwt)
            throws  UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);

        cartService.addCartItem(user.getId(),req);

        ApiResponse res = new ApiResponse();
        res.setMessage("Item added to cart");
        res.setStatus(true);
        return  new ResponseEntity<>(res,HttpStatus.OK);
    }

}
