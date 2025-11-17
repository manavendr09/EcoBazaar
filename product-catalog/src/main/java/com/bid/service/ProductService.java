package com.bid.service;


import com.bid.dto.ProductRequestDto;
import com.bid.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto productDto);

    ProductResponseDto getProductById(Long id);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto updateProduct(Long id, ProductRequestDto productDto);

    String deleteProduct(Long id);

    List<ProductResponseDto> findProductsByCategory(String category);

    List<ProductResponseDto> findProductsByEcoCertified();

    List<ProductResponseDto> findProductsByMaxCarbon(Double maxCarbon);
}