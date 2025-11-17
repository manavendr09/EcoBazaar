package com.bid.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private String details;
    private String category;
    private Double price;
    private Long sellerId;
    private Double carbonImpact;
    private Boolean ecoCertified;
    private LocalDateTime createdAt;
}