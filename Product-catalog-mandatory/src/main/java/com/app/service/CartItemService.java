package com.app.service;

import com.app.model.Cart;
import com.app.model.CartItem;
import com.app.model.Product;
import com.app.repository.CartItemRepository;
import com.app.repository.CartRepository;
import com.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public CartItem addCartItem(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Get or create cart (for simplicity, we assume a single cart)
        Cart cart = cartRepository.findAll().stream().findFirst().orElse(new Cart());
        if (cart.getId() == null) cart = cartRepository.save(cart);

        CartItem cartItem = new CartItem(product, quantity);
        cartItem.setCart(cart);
        return cartItemRepository.save(cartItem);
    }
}
