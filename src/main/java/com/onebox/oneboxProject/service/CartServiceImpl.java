package com.onebox.oneboxProject.service;

import com.onebox.oneboxProject.model.Cart;
import com.onebox.oneboxProject.model.Product;

import com.onebox.oneboxProject.repository.CartRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService
{

    private final CartRepository cartRepository;


    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart createCart() {
        Cart cart = new Cart();
        cartRepository.save(cart);
        return cart;
    }

    public Cart addProductToCart(UUID cartId, Product product) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.addProduct(product);
            cart = cartRepository.save(cart);
            return cart;
        } else {
            throw new RuntimeException("Carrito no encontrado con el ID: " + cartId);
        }
    }


    public void deleteCart(UUID cartId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            cartRepository.delete(cartId);
        } else {
            throw new IllegalStateException("Carrito no encontrado con el ID: " + cartId);
        }
    }

    public Cart getCartById(UUID cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

    @Scheduled(fixedRate = 600000)
    public void removeInactiveCarts() {
        Instant expirationTime = Instant.now().minusSeconds(600);
        cartRepository.findAll().forEach((id, cart) -> {
            if (cart.getLastUpdated().isBefore(expirationTime)) {
                cartRepository.delete(cart.getId());
                System.out.println("Carrito eliminado por inactividad: " + cart.getId());
            }
        });
    }
}