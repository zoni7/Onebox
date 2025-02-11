package com.onebox.oneboxProject.repository;

import com.onebox.oneboxProject.model.Cart;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface CartRepository {

    Cart save(Cart cart);

    Optional<Cart> findById(UUID cartId);

    Map<UUID, Cart> findAll();

    void delete(UUID cartId);

    boolean existsById(UUID cartId);
}