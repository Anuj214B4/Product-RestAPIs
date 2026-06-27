package com.springwithanuj.springboot_datajpa_project.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productBrand;

    private String productName;

    private String productPrice;

    private String productType;

    private Integer stock;
}
