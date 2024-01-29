package com.example.glovoordermanagementapi.service;

import com.example.glovoordermanagementapi.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final List<Product> products;

    public ProductService() {
        this.products = new ArrayList<>();
        products.add(new Product("Product A", 15.99));
        products.add(new Product("Product B", 25.49));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductByName(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        return null;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void updateProduct(Product updatedProduct) {
        String productName = updatedProduct.getName();
        Product existingProduct = getProductByName(productName);

        if (existingProduct != null) {

            existingProduct.setPrice(updatedProduct.getPrice());
        } else {
            System.out.println("Product with name " + productName + " not found. Cannot update.");
        }
    }

    public void deleteProduct(Product product) {
        products.remove(product);
    }

    public Product getProductById(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

}
