package com.bid.repository;

import com.bid.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryIgnoreCase(String category);

    List<Product> findByEcoCertified(Boolean isEcoCertified);

    List<Product> findByCarbonImpactLessThanEqual(Double maxCarbonImpact);
}
