package com.example.ordered_food.repository;

import com.example.ordered_food.model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderedRepository extends JpaRepository<Ordered,Long> {


    @Query("select o from  Ordered  o where  o.user.id= :userId and " +
            "(o.orderStatus = 'PLACED' or o.orderStatus='CONFIRMED' or o.orderStatus ='SHIPPED' or o.orderStatus='DELIVERED') ")
    List<Ordered> getUsersOrders(@Param("userId")Long userId);
}
