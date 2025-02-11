package com.onebox.oneboxProject.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    public void toString_success() {
        UUID id = UUID.randomUUID();
        String description = "Test Product";
        double amount = 99.99;
        Product product = new Product(description, amount);

        String expected = "Product{id='" + product.getId() + "', description='Test Product', amount=99.99}";

        String result = product.toString();

        assertEquals(expected, result);
    }

}