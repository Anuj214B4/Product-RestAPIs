package com.springwithanuj.springboot_datajpa_project.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private Long productId;
    private String productBrand;
    private String productName;
    private String description;
    private Double productPrice;
    private String productCategory;
    private Integer stock;
}
