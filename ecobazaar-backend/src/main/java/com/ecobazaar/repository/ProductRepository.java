package com.ecobazaar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecobazaar.model.Product;

/*
 * ProductRepository.java
 * Simple JPA repository to perform basic CRUD operations on Product.
 * We extend JpaRepository which already provides common methods such as findAll(), save(), deleteById(), etc.
 */
public interface ProductRepository extends JpaRepository<Product, Long> { }
