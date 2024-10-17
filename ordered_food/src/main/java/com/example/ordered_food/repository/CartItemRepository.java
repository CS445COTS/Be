package com.example.ordered_food.repository;

import com.example.ordered_food.model.Cart;
import com.example.ordered_food.model.CartIem;
import com.example.ordered_food.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartIem,Long> {

    @Query("select ci from  CartIem ci where  ci.cart= :cart and ci.product =:product and ci.userId=:userId")
    CartIem isCartITemExist(@Param("cart")Cart cart,
                            @Param("product")Product product,
                            @Param("userId") Long userId
                            );
}
