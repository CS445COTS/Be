package com.example.ordered_food.service.cart;

import com.example.ordered_food.exception.CartItemException;
import com.example.ordered_food.exception.UserException;
import com.example.ordered_food.model.Cart;
import com.example.ordered_food.model.CartIem;
import com.example.ordered_food.model.Product;

public interface CartItemService {


    CartIem createCartItem(CartIem cartIem);

    CartIem updateCartItem(Long userId,Long id,CartIem cartIem) throws CartItemException, UserException;

    CartIem isCartItemExist(Cart cart, Product product,Long userId);


    void removeCartItem(Long userId,Long cartItemId) throws CartItemException, UserException;

    CartIem findCartItemById(Long CartItemId) throws  CartItemException;
}
