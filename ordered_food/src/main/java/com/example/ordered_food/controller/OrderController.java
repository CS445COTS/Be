package com.example.ordered_food.controller;


import com.example.ordered_food.exception.OrderException;
import com.example.ordered_food.exception.UserException;
import com.example.ordered_food.model.Address;
import com.example.ordered_food.model.Ordered;
import com.example.ordered_food.model.User;
import com.example.ordered_food.service.order.OrderService;
import com.example.ordered_food.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


    @PostMapping("/")
    public ResponseEntity<Ordered> createOrder(@RequestBody Address shippingAddress,
                                               @RequestHeader("Authorization")String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        Ordered ordered = orderService.createOrder(user,shippingAddress);
        return  new ResponseEntity<Ordered>(ordered, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public  ResponseEntity<List<Ordered>> UserOrdersHistory(@RequestHeader("Authorization")String jwt) throws UserException{
        User user = userService.findUserProfileByJwt(jwt);

        List<Ordered> ordereds = orderService.usersOrderHistory(user.getId());
        return  new ResponseEntity<>(ordereds,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ordered> findOrderById(@PathVariable("id")Long orderId,
                                                 @RequestHeader("Authorization")String jwt) throws UserException, OrderException, OrderException {
        User user = userService.findUserProfileByJwt(jwt);
        Ordered ordered = orderService.findOrderById(orderId);

        return  new ResponseEntity<>(ordered,HttpStatus.ACCEPTED);
    }

}
