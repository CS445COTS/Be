package com.example.ordered_food.service.order;

import com.example.ordered_food.exception.OrderException;
import com.example.ordered_food.model.Address;
import com.example.ordered_food.model.Ordered;
import com.example.ordered_food.model.User;

import java.util.List;

public interface OrderService {
    Ordered createOrder(User user, Address shippingAddress);

    Ordered findOrderById(Long orderdId) throws OrderException;
    List<Ordered> usersOrderHistory(Long userId);

    Ordered placedOrder(Long orderId) throws OrderException;
    Ordered confirmedOrder(Long orderId) throws OrderException;
    Ordered shippedOrder(Long orderId) throws OrderException;
    Ordered deliveredOrder(Long orderId) throws OrderException;
    Ordered cancledOrder(Long orderId) throws OrderException;

    List<Ordered> getAllOrders();
    void deleteOrder(Long orderId) throws OrderException;
}
