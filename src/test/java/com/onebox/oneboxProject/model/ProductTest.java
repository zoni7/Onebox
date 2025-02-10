package com.onebox.oneboxProject.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    public void toString_success() {
        // Arrange
        UUID id = UUID.randomUUID();
        String description = "Test Product";
        double amount = 99.99;
        Product product = new Product(description, amount);

        // Replace id in the output with the generated id for verification
        String expected = "Product{id='" + product.getId() + "', description='Test Product', amount=99.99}";

        // Act
        String result = product.toString();

        // Assert
        assertEquals(expected, result);
    }

}