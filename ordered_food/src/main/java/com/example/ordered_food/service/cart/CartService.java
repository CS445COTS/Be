package com.example.ordered_food.service.cart;

import com.example.ordered_food.exception.ProductException;
import com.example.ordered_food.model.Cart;
import com.example.ordered_food.model.User;
import com.example.ordered_food.request.AddItemRequest;

public interface CartService {


    Cart createCart(User user);


    String addCartItem(Long userId, AddItemRequest req) throws ProductException;
    Cart findUserCart(Long userId);
}
