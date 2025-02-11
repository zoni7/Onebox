package com.onebox.oneboxProject.repository;

import com.onebox.oneboxProject.model.Cart;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartRepositoryImplTest {

    private final CartRepositoryImpl cartRepository = new CartRepositoryImpl();

    /**
     * Test class for CartRepositoryImpl.
     * The save method is responsible for saving or updating a Cart object in the repository.
     * Each test focuses on a single use case of the save method.
     */

    @Test
    void save_ShouldSaveNewCart_success() {
        // Arrange
        Cart cart = new Cart();

        // Act
        Cart savedCart = cartRepository.save(cart);

        // Assert
        assertNull(savedCart, "A new cart should return null as it is not replacing an existing cart.");
        assertTrue(cartRepository.existsById(cart.getId()), "Cart should exist after being saved.");
        assertEquals(cart, cartRepository.findById(cart.getId()).orElse(null), "The saved cart should be retrievable.");
    }

}