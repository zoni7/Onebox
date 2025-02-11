package com.onebox.oneboxProject.service;

import com.onebox.oneboxProject.model.Cart;
import com.onebox.oneboxProject.model.Product;

import java.util.UUID;

public interface CartService {
    Cart createCart();
    Cart addProductToCart(UUID cartId, Product product);
    void deleteCart(UUID cartId);
    Cart getCartById(UUID cartId);
    void removeInactiveCarts();
}