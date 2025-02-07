package com.onebox.oneboxProject.model;

import lombok.Data;

@Data
public class Product {
    private final String id;
    private String description;
    private double amount;

    public Product(String id, String description, double amount) {
        this.id = id;
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