package com.app.dto;

import java.util.List;

public class CartResponse {
    private Long cartId;
    private Double totalPrice;
    private List<CartItemDTO> items;

    // Constructor
    public CartResponse(Long cartId, Double totalPrice, List<CartItemDTO> items) {
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.items = items;
    }

    // Getters and Setters
    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }
}
