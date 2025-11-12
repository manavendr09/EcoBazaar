package com.ecobazaar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecobazaar.model.Product;
import com.ecobazaar.repository.ProductRepository;

/*
 * ProductService.java
 * Business logic lives here. Right now it forwards calls to the repository.
 * Keep controllers thin and put more complex logic in services.
 */
@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    // Return all products from DB
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    // Add a new product to DB
    public Product addProduct(Product product) {
        return repo.save(product);
    }
}
