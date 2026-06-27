package com.springwithanuj.springboot_datajpa_project.service;

import com.springwithanuj.springboot_datajpa_project.model.Product;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);
    List<Product> saveProducts(List<Product> products);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    String deleteProductById(Long id);

    Object searchProductByBrand(String brand);

    String updateProductByName(String name, Product product);

    List<Product> getAll();
}
