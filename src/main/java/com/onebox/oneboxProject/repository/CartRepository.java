package com.onebox.oneboxProject.repository;

import com.onebox.oneboxProject.model.Cart;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CartRepository {
    // Simulación de una base de datos en memoria
    private final Map<UUID, Cart> databaseMock = new HashMap<>();

    public Cart save(Cart cart) {
        return databaseMock.put(cart.getId(), cart);
    }

    public Optional<Cart> findById(UUID cartId) {
        return Optional.ofNullable(databaseMock.get(cartId));
    }

    public Map<UUID, Cart> findAll() {
        return databaseMock;
    }

    public void delete(UUID cartId) {
        databaseMock.remove(cartId);
    }

    public boolean existsById(UUID cartId) {
        return databaseMock.containsKey(cartId);
    }
}