package com.example.ordered_food.model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Cart {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private  Long id;


    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    private  User user;


    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
    @Column(name = "cart_items")
    private Set<CartIem> cartIems = new HashSet<>();

    @Column(name = "total_price")
    private  double totalPrice;

    @Column(name = "total_item")
    private  int totalItem;

    private int totalDiscountedPrice;

    private  int discounted;

    public Cart() {
    }

    public Cart(Long id, User user, Set<CartIem> cartIems, double totalPrice, int totalItem, int totalDiscountedPrice, int discounted) {
        this.id = id;
        this.user = user;
        this.cartIems = cartIems;
        this.totalPrice = totalPrice;
        this.totalItem = totalItem;
        this.totalDiscountedPrice = totalDiscountedPrice;
        this.discounted = discounted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<CartIem> getCartIems() {
        return cartIems;
    }

    public void setCartIems(Set<CartIem> cartIems) {
        this.cartIems = cartIems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public int getTotalDiscountedPrice() {
        return totalDiscountedPrice;
    }

    public void setTotalDiscountedPrice(int totalDiscountedPrice) {
        this.totalDiscountedPrice = totalDiscountedPrice;
    }

    public int getDiscounted() {
        return discounted;
    }

    public void setDiscounted(int discounted) {
        this.discounted = discounted;
    }
}
