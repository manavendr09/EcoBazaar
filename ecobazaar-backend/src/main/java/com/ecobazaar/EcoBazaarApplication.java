package com.ecobazaar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ecobazaar.model.Product;
import com.ecobazaar.repository.ProductRepository;

/*
 * EcoBazaarApplication.java
 * Main application class for Spring Boot.
 * Simple, commented, and loads sample product data at startup.
 */
@SpringBootApplication
public class EcoBazaarApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcoBazaarApplication.class, args);
    }

    // Load some sample products into the H2 in-memory database at startup.
    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {
        return args -> {
            productRepository.save(new Product(null, "Organic Wheat", "Pure organic wheat from local farms", 299.0));
            productRepository.save(new Product(null, "Eco Bottle", "Reusable stainless steel water bottle", 499.0));
            productRepository.save(new Product(null, "Reusable Bag", "Eco-friendly shopping bag", 199.0));
        };
    }
}
