package com.onebox.oneboxProject.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Product {
    private final UUID id;
    private String description;
    private double amount;

    public Product( String description, double amount) {
        this.id = UUID.randomUUID();
        this.description = description;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}