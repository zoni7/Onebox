package com.onebox.oneboxProject.model;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CartTest {

    @Test
    void testToString_success() {
        // Arrange
        Cart cart = new Cart();
        Product product1 = new Product( "Product1", 10.99);
        Product product2 = new Product( "Product2", 5.49);
        cart.addProduct(product1);
        cart.addProduct(product2);

        UUID expectedId = cart.getId();
        Instant expectedLastUpdated = cart.getLastUpdated();
        List<Product> expectedProducts = cart.getProducts();

        // Act
        String result = cart.toString();

        // Assert
        String expected = "Cart{" +
                "id='" + expectedId + '\'' +
                ", lastActivity=" + expectedLastUpdated +
                ", products=" + expectedProducts +
                '}';
        assertEquals(expected, result);
    }

    @Test
    void addProduct_singleProduct_success() {
        // Arrange
        Cart cart = new Cart();
        Product product = new Product("Product1", 10.99);

        // Act
        cart.addProduct(product);

        // Assert
        assertEquals(1, cart.getProducts().size());
        assertEquals(product, cart.getProducts().get(0));
    }

    @Test
    void addProduct_multipleProducts_success() {
        // Arrange
        Cart cart = new Cart();
        Product product1 = new Product("Product1", 10.99);
        Product product2 = new Product("Product2", 5.49);

        // Act
        cart.addProduct(product1);
        cart.addProduct(product2);

        // Assert
        assertEquals(2, cart.getProducts().size());
        assertEquals(product1, cart.getProducts().get(0));
        assertEquals(product2, cart.getProducts().get(1));
    }

}