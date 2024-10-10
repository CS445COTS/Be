package com.example.ordered_food.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ordered {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private  Long id;


    @Column(name = "order_id")
    private String orderId;


    @ManyToOne
    private  User user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderdate;

    private LocalDateTime deliveryDate;


    @OneToOne
    private Address shippingAddress;

    @Embedded
    private  PaymentDetails paymentDetails = new PaymentDetails();

    private  double totalPrice;
    private  Integer totalDiscountedPrice;

    private Integer discounted;

    private  String orderStatus;

    private  int totalItem;


    private  LocalDateTime createAt;


    public Ordered() {
    }

    public Ordered(Long id, String orderId, User user, List<OrderItem> orderItems, LocalDateTime orderdate, LocalDateTime deliveryDate, Address shippingAdress, PaymentDetails paymentDetails, double totalPrice, Integer totalDiscountedPrice, Integer discounted, String orderStatus, int totalItem, LocalDateTime createAt) {
        this.id = id;
        this.orderId = orderId;
        this.user = user;
        this.orderItems = orderItems;
        this.orderdate = orderdate;
        this.deliveryDate = deliveryDate;
        this.shippingAddress = shippingAdress;
        this.paymentDetails = paymentDetails;
        this.totalPrice = totalPrice;
        this.totalDiscountedPrice = totalDiscountedPrice;
        this.discounted = discounted;
        this.orderStatus = orderStatus;
        this.totalItem = totalItem;
        this.createAt = createAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public LocalDateTime getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(LocalDateTime orderdate) {
        this.orderdate = orderdate;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Address getShippingAdress() {
        return shippingAddress;
    }

    public void setShippingAdress(Address shippingAdress) {
        this.shippingAddress = shippingAdress;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalDiscountedPrice() {
        return totalDiscountedPrice;
    }

    public void setTotalDiscountedPrice(Integer totalDiscountedPrice) {
        this.totalDiscountedPrice = totalDiscountedPrice;
    }

    public Integer getDiscounted() {
        return discounted;
    }

    public void setDiscounted(Integer discounted) {
        this.discounted = discounted;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
