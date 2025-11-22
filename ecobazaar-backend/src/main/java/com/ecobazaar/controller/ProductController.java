package com.ecobazaar.controller;


import com.ecobazaar.dtos.ProductFilterDTO;
import com.ecobazaar.dtos.ProductRequestDTO;
import com.ecobazaar.dtos.ProductResponseDTO;
import com.ecobazaar.dtos.ProductUpdateDTO;
import com.ecobazaar.service.FileStorageService;
import com.ecobazaar.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Product Catalog", description = "Carbon-Aware Product Management APIs")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;
    private final FileStorageService fileStorageService;

    @PostMapping(consumes = {"multipart/form-data"})
    @Operation(summary = "Create a new product with carbon footprint data")
    public ResponseEntity<ProductResponseDTO> createProduct(
            @Valid @RequestPart("product") ProductRequestDTO request,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        log.info("REST request to create product: {}", request.getName());
        ProductResponseDTO response = productService.createProduct(request, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping(value = "/{id}", consumes = {"multipart/form-data"})
    @Operation(summary = "Partially update an existing product")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Long id,
            @Valid @RequestPart("product") ProductUpdateDTO request,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        log.info("REST request to PATCH product with ID: {}", id);
        ProductResponseDTO response = productService.updateProduct(id, request, file);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product details by ID")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        log.info("REST request to get product with ID: {}", id);
        ProductResponseDTO response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get all active products")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        log.info("REST request to get all products");
        List<ProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search")
    @Operation(summary = "Search products by keyword in name or description")
    public ResponseEntity<List<ProductResponseDTO>> searchProducts(
            @RequestParam String keyword) {
        log.info("REST request to search products with keyword: {}", keyword);
        List<ProductResponseDTO> products = productService.searchProducts(keyword);
        return ResponseEntity.ok(products);
    }

    @PostMapping("/filter")
    @Operation(summary = "Filter products with multiple criteria")
    public ResponseEntity<List<ProductResponseDTO>> filterProducts(
            @RequestBody ProductFilterDTO filter) {
        log.info("REST request to filter products");
        List<ProductResponseDTO> products = productService.filterProducts(filter);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/low-carbon")
    @Operation(summary = "Get products with carbon footprint below threshold")
    public ResponseEntity<List<ProductResponseDTO>> getLowCarbonProducts(
            @RequestParam(defaultValue = "5.0") BigDecimal maxImpact) {
        log.info("REST request to get low carbon products (max: {})", maxImpact);
        List<ProductResponseDTO> products = productService.getLowCarbonProducts(maxImpact);
        return ResponseEntity.ok(products);
    }

    /**
     * Get eco-certified products
     */
    @GetMapping("/eco-certified")
    @Operation(summary = "Get all eco-certified products")
    public ResponseEntity<List<ProductResponseDTO>> getEcoCertifiedProducts() {
        log.info("REST request to get eco-certified products");
        List<ProductResponseDTO> products = productService.getEcoCertifiedProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Get eco-friendly alternatives
     */
    @GetMapping("/{id}/alternatives")
    @Operation(summary = "Get eco-friendly alternatives for a product")
    public ResponseEntity<List<ProductResponseDTO>> getEcoAlternatives(
            @PathVariable Long id) {
        log.info("REST request to get alternatives for product: {}", id);
        List<ProductResponseDTO> alternatives = productService.getEcoAlternatives(id);
        return ResponseEntity.ok(alternatives);
    }

    /**
     * Delete product (soft delete)
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product (soft delete)")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.info("REST request to delete product with ID: {}", id);
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Serve product images
     */
    @GetMapping("/images/{filename:.+}")
    @Operation(summary = "Get product image by filename")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path filePath = fileStorageService.getFilePath(filename);
            Resource resource = new UrlResource(filePath.toUri());
            
            if (resource.exists() && resource.isReadable()) {
                // Determine content type
                String contentType = Files.probeContentType(filePath);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }
                
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Error serving image: {}", filename, e);
            return ResponseEntity.notFound().build();
        }
    }
}