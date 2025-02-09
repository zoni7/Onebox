package com.onebox.oneboxProject.model;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Cart {
    private UUID id;
    private LocalDateTime lastActivity;
    private List<Product> products;

    public Cart() {
        this.id = UUID.randomUUID();
        this.lastActivity = LocalDateTime.now();
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
        this.lastActivity = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", lastActivity=" + lastActivity +
                ", products=" + products +
                '}';
    }

}