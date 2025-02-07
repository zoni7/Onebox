package com.onebox.oneboxProject.repository;

import com.onebox.oneboxProject.model.Cart;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CartRepository {
    // Simulación de una base de datos en memoria
    private final Map<String, Cart> databaseMock = new HashMap<>();

    // Crear o actualizar una cart
    public void save(Cart cart) {
        databaseMock.put(cart.getId(), cart);
    }

    // Buscar una cart por ID
    public Optional<Cart> findById(String cartId) {
        return Optional.ofNullable(databaseMock.get(cartId));
    }

    // Obtener todas las carts (extraer todos los registros)
    public Map<String, Cart> findAll() {
        return databaseMock;
    }

    // Eliminar una cart por ID
    public void delete(String cartId) {
        databaseMock.remove(cartId);
    }

    // Verificar si existe una cart por ID
    public boolean existsById(String cartId) {
        return databaseMock.containsKey(cartId);
    }
}