package com.example.ordered_food.service.order;


import com.example.ordered_food.exception.OrderException;
import com.example.ordered_food.model.*;
import com.example.ordered_food.repository.AddressRepository;
import com.example.ordered_food.repository.OrderItemRepository;
import com.example.ordered_food.repository.OrderedRepository;
import com.example.ordered_food.repository.UserRepository;
import com.example.ordered_food.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImlp implements OrderService {

    @Autowired
    private CartService cartService;
    @Autowired
    private OrderedRepository orderedRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Ordered createOrder(User user, Address shippingAddress) {
        shippingAddress.setUser(user);
        Address address = addressRepository.save(shippingAddress);
        user.getAddress().add(address);
        userRepository.save(user);

        Cart cart = cartService.findUserCart(user.getId());
        List<OrderItem> orderitems = new ArrayList<>();

        for (CartIem item : cart.getCartIems()){
            OrderItem orderitem = new OrderItem();

            orderitem.setPrice(item.getPrice());
            orderitem.setProduct(item.getProduct());
            orderitem.setQuantity(item.getQuantity());
            orderitem.setUserId(item.getUserId());
            orderitem.setDiscountedPrice(item.getDiscountedPrice());
            OrderItem createdOrderItem = orderItemRepository.save(orderitem);
            orderitems.add(createdOrderItem);
        }

        Ordered createdOrdered = new Ordered();
        createdOrdered.setUser(user);
        createdOrdered.setOrderItems(orderitems);
        createdOrdered.setTotalPrice(cart.getTotalPrice());
        createdOrdered.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
        createdOrdered.setDiscounted(cart.getDiscounted());
        createdOrdered.setTotalItem(cart.getTotalItem());

        createdOrdered.setShippingAdress(address);
        createdOrdered.setOrderdate(LocalDateTime.now());
        createdOrdered.setOrderStatus("PENDING");
        createdOrdered.getPaymentDetails().setStatus("PENDING");
        createdOrdered.setCreateAt(LocalDateTime.now());

        Ordered saveOrder = orderedRepository.save(createdOrdered);

        for(OrderItem orderitem : orderitems){
            orderitem.setOrdered(saveOrder);
            orderItemRepository.save(orderitem);
        }

        return saveOrder;
    }

    @Override
    public Ordered findOrderById(Long orderdId) throws OrderException {
        Optional<Ordered> opt = orderedRepository.findById(orderdId);

        if(opt.isPresent()){
            return  opt.get();
        }
        throw new OrderException("order not exist with id :"+orderdId);
    }

    @Override
    public List<Ordered> usersOrderHistory(Long userId) {
        List<Ordered> ordereds = orderedRepository.getUsersOrders(userId);
        return  ordereds;
    }

    @Override
    public Ordered placedOrder(Long orderId) throws OrderException {
        Ordered ordered = findOrderById(orderId);
        ordered.setOrderStatus("PLACED");
        ordered.getPaymentDetails().setStatus("COMPLETED");
        return ordered;
    }

    @Override
    public Ordered confirmedOrder(Long orderId) throws OrderException {
        Ordered ordered = findOrderById(orderId);
        ordered.setOrderStatus("CONFIRMED");

        return orderedRepository.save(ordered);
    }

    @Override
    public Ordered shippedOrder(Long orderId) throws OrderException {
        Ordered ordered = findOrderById(orderId);
        ordered.setOrderStatus("SHIPPED");
        return orderedRepository.save(ordered);
    }

    @Override
    public Ordered deliveredOrder(Long orderId) throws OrderException {
        Ordered ordered = findOrderById(orderId);
        ordered.setOrderStatus("DELIVERED");

        return orderedRepository.save(ordered);
    }

    @Override
    public Ordered cancledOrder(Long orderId) throws OrderException {
        Ordered ordered = findOrderById(orderId);
        ordered.setOrderStatus("CANCELLED");

        return orderedRepository.save(ordered);
    }

    @Override
    public List<Ordered> getAllOrders() {
        return orderedRepository.findAll();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
        Ordered ordered = findOrderById(orderId);

        orderedRepository.deleteById(orderId);

    }
}
