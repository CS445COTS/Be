package com.example.ordered_food.controller;


import com.example.ordered_food.exception.CartItemException;
import com.example.ordered_food.exception.UserException;
import com.example.ordered_food.model.CartIem;
import com.example.ordered_food.model.User;
import com.example.ordered_food.service.cart.CartItemService;
import com.example.ordered_food.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {

    @Autowired
    CartItemService cartItemService;
    @Autowired
    UserService userService;


    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartIem> updateCartitem(@RequestHeader("Authorization")String jwt,
                                                  @PathVariable Long cartItemId,
                                                  @RequestBody CartIem cartIem) throws UserException, CartItemException {
        User user = userService.findUserProfileByJwt(jwt);


        CartIem cartIem1 = cartItemService.updateCartItem(user.getId(),cartItemId,cartIem);

        return  new ResponseEntity<CartIem>(cartIem1, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{cartItemId}")
    public  ResponseEntity<?> removeCartItem(@RequestHeader("Authorization")String jwt,
                                             @PathVariable Long cartItemId) throws UserException, CartItemException {

        User user = userService.findUserProfileByJwt(jwt);
        cartItemService.removeCartItem(user.getId(),cartItemId);

        return  new ResponseEntity<>("item deleted success",HttpStatus.OK);
    }
}
