package com.example.ordered_food.repository;

import com.example.ordered_food.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart,Long> {


    @Query("select c from  Cart c where c.user.id =:userId")
    Cart findUserById(@Param("userId") Long userId);
}
