package com.springwithanuj.springboot_datajpa_project.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    private Long productId;

    @NotBlank(message = "Product brand is required")
    @Size(max = 100, message = "Product brand must not exceed 100 characters")
    private String productBrand;

    @NotBlank(message = "Product name is required")
    @Size(max = 150, message = "Product name must not exceed 150 characters")
    private String productName;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String description;


    @NotBlank(message = "Product price is required")
    @Positive(message = "Price must be a valid number with up to 2 decimal places")
    private Double productPrice;

    @NotBlank(message = "Product category is required")
    @Size(max = 100, message = "Product category must not exceed 100 characters")
    private String productCategory;

    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock;
}
