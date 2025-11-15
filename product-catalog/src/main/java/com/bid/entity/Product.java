package com.bid.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String details;

    @Column(length = 100)
    private String category;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Long sellerId;

    @Column(nullable = false)
    private Double carbonImpact;

    @Column(nullable = false)
    private Boolean ecoCertified = false;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
