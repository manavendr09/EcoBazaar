package com.bid.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequestDto {

    @NotBlank(message = "Name is required")
    @Size(max = 150)
    private String name;

    private String details;

    @NotBlank(message = "Category is required")
    @Size(max = 100)
    private String category;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;

    @NotNull(message = "Seller ID is required")
    private Long sellerId;

    @NotNull(message = "Carbon Impact is required")
    @Positive(message = "Carbon Impact must be positive")
    private Double carbonImpact;


    private Boolean ecoCertified=false;
}
