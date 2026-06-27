package com.springwithanuj.springboot_datajpa_project.service;

import com.springwithanuj.springboot_datajpa_project.dtos.ProductRequest;
import com.springwithanuj.springboot_datajpa_project.model.Product;

import java.util.List;

public interface ProductService {

    Product saveProduct(ProductRequest productRequest);
    List<Product> saveProducts(List<ProductRequest> productRequests);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    String deleteProductById(Long id);

    Object searchProductByBrand(String brand);

    String updateProductByName(String name, Product product);

    List<Product> getAll();
}
