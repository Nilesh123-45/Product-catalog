package com.app.controller;

import com.app.model.CartItem;
import com.app.service.CartItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping
    public CartItem addCartItem(@RequestParam Long productId, @RequestParam int quantity) {
        return cartItemService.addCartItem(productId, quantity);
    }
}
