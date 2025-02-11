package com.onebox.oneboxProject.model;

import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Cart {
    private UUID id;
    private Instant lastUpdated;
    private List<Product> products;

    public Cart() {
        this.id = UUID.randomUUID();
        this.lastUpdated = Instant.now();
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        this.products.add(product);
        updateLastActivity();
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        updateLastActivity();
    }

    private void updateLastActivity() {
        this.lastUpdated = Instant.now();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", lastActivity=" + lastUpdated +
                ", products=" + products +
                '}';
    }

}