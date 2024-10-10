package com.example.ordered_food.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class OrderItem {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private  Long id ;


    @ManyToOne
    @JsonIgnore
    private  Ordered order;

    @ManyToOne
    private Product product;

    private int quantity;

    private  Integer price;

    private  Integer discountedPrice;

    private  Long userId;

    private LocalDateTime deliveryDate;

    public OrderItem() {
    }

    public OrderItem(Long id, Ordered ordered, Product product, int quantity, Integer price, Integer discountedPrice, Long userId, LocalDateTime deliveryDate) {
        this.id = id;
        this.order = ordered;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.userId = userId;
        this.deliveryDate = deliveryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ordered getOrdered() {
        return order;
    }

    public void setOrdered(Ordered ordered) {
        this.order = ordered;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Integer discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
