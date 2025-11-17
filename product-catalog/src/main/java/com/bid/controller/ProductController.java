package com.bid.controller;

import com.bid.dto.ProductRequestDto;
import com.bid.dto.ProductResponseDto;
import com.bid.dto.SuccessDto;
import com.bid.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(
            @Valid @RequestBody ProductRequestDto productDto) {

        ProductResponseDto createdProduct = productService.createProduct(productDto);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDto productDto) {

        ProductResponseDto updatedProduct = productService.updateProduct(id, productDto);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessDto> deleteProduct(@PathVariable Long id) {
        String message = productService.deleteProduct(id);
        return ResponseEntity.ok(new SuccessDto(message));
    }

    // --- Search Methods ---

    @GetMapping("/search/category")
    public ResponseEntity<List<ProductResponseDto>> searchByCategory(@RequestParam String category) {
        return ResponseEntity.ok(productService.findProductsByCategory(category));
    }

    @GetMapping("/search/eco")
    public ResponseEntity<List<ProductResponseDto>> searchByEcoCertified() {
        return ResponseEntity.ok(productService.findProductsByEcoCertified());
    }

    @GetMapping("/search/max-carbon")
    public ResponseEntity<List<ProductResponseDto>> searchByMaxCarbon(@RequestParam Double maxCarbon) {
        return ResponseEntity.ok(productService.findProductsByMaxCarbon(maxCarbon));
    }
}