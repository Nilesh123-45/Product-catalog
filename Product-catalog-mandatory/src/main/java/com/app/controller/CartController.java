package com.app.controller;

import com.app.dto.CartItemDTO;
import com.app.dto.CartRequest;
import com.app.dto.CartResponse;
import com.app.model.Cart;
import com.app.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CartResponse addProductToCart(@RequestBody CartRequest cartRequest) {
        Cart cart = cartService.addProductToCart(cartRequest.getUserId(), cartRequest.getProductId(), cartRequest.getQuantity());
        return convertToResponse(cart);
    }

    @GetMapping("/{userId}")
    public CartResponse getCart(@PathVariable Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return convertToResponse(cart);
    }

    @DeleteMapping("/remove")
    public CartResponse removeProductFromCart(@RequestParam Long userId, @RequestParam Long productId) {
        Cart cart = cartService.removeProductFromCart(userId, productId);
        return convertToResponse(cart);
    }

    private CartResponse convertToResponse(Cart cart) {
        List<CartItemDTO> itemDTOs = cart.getItems().stream()
                .map(item -> new CartItemDTO(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPrice()))
                .collect(Collectors.toList());
        return new CartResponse(cart.getId(), cart.getTotalPrice(), itemDTOs);
    }
}
