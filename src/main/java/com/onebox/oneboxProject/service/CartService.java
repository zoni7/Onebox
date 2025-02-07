package com.onebox.oneboxProject.service;

import com.onebox.oneboxProject.model.Cart;
import com.onebox.oneboxProject.model.Product;
import com.onebox.oneboxProject.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;

    // Constructor para inyectar la capa de repositorio
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // Crear una nueva cart
    public Cart createCart(Cart cart) {
        cartRepository.save(cart);
        return cart;
    }

    // Obtener una cart por ID
    public Optional<Cart> getCartById(String cartId) {
        return cartRepository.findById(cartId);
    }

    // Añadir un producto a una cart
    public void addProductToCart(String cartId, Product product) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.addProduct(product);
            cartRepository.save(cart); // Guardar los cambios
        } else {
            throw new RuntimeException("Cart no encontrada con el ID: " + cartId);
        }
    }

    // Eliminar un producto de la cart
    public void removeProductFromCart(String cartId, Product product) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.removeProduct(product);
            cartRepository.save(cart); // Guardar los cambios
        } else {
            throw new RuntimeException("Cart no encontrada con el ID: " + cartId);
        }
    }

    // Eliminar una cart
    public void deleteCart(String cartId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            cartRepository.delete(cartId);
        } else {
            throw new IllegalStateException("Cart no encontrada con el ID: " + cartId);
        }
    }

    public Cart getCart(String cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

}