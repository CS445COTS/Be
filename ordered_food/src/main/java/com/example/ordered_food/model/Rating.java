package com.example.ordered_food.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Rating {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private  User user;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id",nullable = false)
    private  Product product;


    @Column(name = "rating")
    private  double rating;

    private LocalDateTime createAt;

    public Rating() {
    }

    public Rating(Long id, User user, Product product, double rating, LocalDateTime createAt) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.rating = rating;
        this.createAt = createAt;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
