package com.example.ordered_food.controller;


import com.example.ordered_food.exception.OrderException;
import com.example.ordered_food.model.Ordered;
import com.example.ordered_food.response.ApiResponse;
import com.example.ordered_food.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {


    @Autowired
    private OrderService orderService;


    @GetMapping("/")
    public ResponseEntity<List<Ordered>> getAllOrder(){
        List<Ordered> ordereds = orderService.getAllOrders();
        return  new ResponseEntity<List<Ordered>>(ordereds, HttpStatus.ACCEPTED);
    }
    @PutMapping("/{orderId}/confirmed")
    public ResponseEntity<Ordered> confirmedOrder(@PathVariable Long orderId,
                                                  @RequestHeader("Authorization")String jwt) throws OrderException {
        Ordered ordered = orderService.confirmedOrder(orderId);

        return  new ResponseEntity<>(ordered,HttpStatus.OK);

    }

    @PutMapping("/{orderId}/ship")
    public  ResponseEntity<Ordered> shippedOrder(@PathVariable Long orderId,
                                                 @RequestHeader("Authorization")String jwt) throws  OrderException{
        Ordered ordered = orderService.shippedOrder(orderId);
        return  new ResponseEntity<>(ordered,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/deliver")
    public ResponseEntity<Ordered>  DeliverOrderHandler(@PathVariable Long orderId,
                                                        @RequestHeader("Authorization")String jwt) throws  OrderException{
        Ordered ordered = orderService.deliveredOrder(orderId);

        return  new ResponseEntity<>(ordered,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Ordered>  CancelOrder(@PathVariable Long orderId,
                                                @RequestHeader("Authorization")String jwt) throws  OrderException{
        Ordered ordered = orderService.cancledOrder(orderId);

        return  new ResponseEntity<>(ordered,HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}/delete")
    public ResponseEntity<ApiResponse>  DeleteOrder(@PathVariable Long orderId,
                                                    @RequestHeader("Authorization")String jwt) throws  OrderException{
        orderService.deleteOrder(orderId);

        ApiResponse res = new ApiResponse();
        res.setMessage("order deleted successfully");
        res.setStatus(true);

        return  new ResponseEntity<>(res,HttpStatus.OK);
    }

}
