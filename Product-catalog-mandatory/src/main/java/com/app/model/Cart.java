package com.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("carts") // Ignore 'carts' to prevent circular reference to user
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("cart") // Ignore 'cart' to prevent circular reference to CartItem
    private List<CartItem> items;

    private Double totalPrice;

    // Default constructor
    public Cart() {}

    // Constructor for creating a cart with a user
    public Cart(User user) {
        this.user = user;
        this.totalPrice = 0.0;
    }

    public void calculateTotalPrice() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        this.totalPrice = total;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
        calculateTotalPrice();
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
