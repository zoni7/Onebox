package com.onebox.oneboxProject.service;

import com.onebox.oneboxProject.model.Cart;
import com.onebox.oneboxProject.model.Product;
import com.onebox.oneboxProject.repository.CartRepository;
import io.micrometer.observation.ObservationFilter;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {

    private final CartRepository cartRepository;

    // Constructor para inyectar la capa de repositorio
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart createCart() {
        Cart cart = new Cart();
        cartRepository.save(cart);
        return cart;
    }

    // Añadir un producto a una cart
    public Cart addProductToCart(UUID cartId, Product product) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.addProduct(product);
            cart = cartRepository.save(cart); // Guardar los cambios
            return cart;
        } else {
            throw new RuntimeException("Cart no encontrada con el ID: " + cartId);
        }
    }


    // Eliminar una cart
    public void deleteCart(UUID cartId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            cartRepository.delete(cartId);
        } else {
            throw new IllegalStateException("Cart no encontrada con el ID: " + cartId);
        }
    }

    public Cart getCartById(UUID cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

}