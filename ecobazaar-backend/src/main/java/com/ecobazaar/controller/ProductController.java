package com.ecobazaar.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ecobazaar.model.Product;
import com.ecobazaar.service.ProductService;

/*
 * ProductController.java
 * Exposes REST APIs at /api/products
 * - GET /api/products  -> list all products
 * - POST /api/products -> add a product
 *
 * CORS is enabled for http://localhost:5173 (frontend dev server)
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // GET all products
    @GetMapping
    public List<Product> getAll() {
        return service.getAllProducts();
    }

    // POST create product
    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.addProduct(product);
    }
}
