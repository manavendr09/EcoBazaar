package com.bid.service;

import com.bid.dto.ProductRequestDto;
import com.bid.dto.ProductResponseDto;
import com.bid.entity.Product;
import com.bid.exception.ResourceNotFoundException;
import com.bid.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productDto) {

        // 1. Create a NEW, EMPTY product. Its 'id' will be null.
        Product product = new Product();

        // 2. Manually set the fields from the DTO
        product.setName(productDto.getName());
        product.setDetails(productDto.getDetails());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setSellerId(productDto.getSellerId()); // This now goes to the correct field
        product.setCarbonImpact(productDto.getCarbonImpact());
//         product.setEcoCertified(productDto.getEcoCertified()); // This is already false as the default

        // 3. Set the server-side fields
        product.setCreatedAt(LocalDateTime.now());

        // 4. Now, when you save, JPA knows this is a new product
        Product savedProduct = productRepository.save(product);

        // You can still use modelMapper for the response, that's perfectly safe.
        return modelMapper.map(savedProduct, ProductResponseDto.class);
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return modelMapper.map(product, ProductResponseDto.class);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        // manual mapping here for updates
        existingProduct.setName(productDto.getName());
        existingProduct.setDetails(productDto.getDetails());
        existingProduct.setCategory(productDto.getCategory());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setSellerId(productDto.getSellerId());
        existingProduct.setCarbonImpact(productDto.getCarbonImpact());
        existingProduct.setEcoCertified(productDto.getEcoCertified());

        Product updatedProduct = productRepository.save(existingProduct);

        return modelMapper.map(updatedProduct, ProductResponseDto.class);
    }

    @Override
    public String deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        productRepository.delete(product);

        return "Product with ID " + id + " deleted successfully.";
    }

    // --- Search Methods ---

    @Override
    public List<ProductResponseDto> findProductsByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category).stream()
                .map(product -> modelMapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> findProductsByEcoCertified() {
        return productRepository.findByEcoCertified(true).stream()
                .map(product -> modelMapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> findProductsByMaxCarbon(Double maxCarbon) {
        return productRepository.findByCarbonImpactLessThanEqual(maxCarbon).stream()
                .map(product -> modelMapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());
    }
}