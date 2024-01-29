package com.example.glovoordermanagementapi.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Data
@Setter
public class Product {
    private int id;
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void displayProductInfo() {
        System.out.println("Product: " + name);
        System.out.println("Price: $" + price);
    }
}
