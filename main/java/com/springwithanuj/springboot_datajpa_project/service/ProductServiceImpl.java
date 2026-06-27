package com.springwithanuj.springboot_datajpa_project.service;

import com.springwithanuj.springboot_datajpa_project.model.Product;
import com.springwithanuj.springboot_datajpa_project.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> saveProducts(List<Product> products) {
        return productRepository.saveAllAndFlush(products);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository
                .findById(id).orElseThrow(
                        () -> new RuntimeException("Product with this id " + id + " is not found!!")
                );
    }

    @Override
    public String deleteProductById(Long id) {
        Product product = productRepository
                .findById(id).orElseThrow(
                        () -> new RuntimeException("Product with this id " + id + " is not found!!")
                );
        productRepository.delete(product);
        return "Product with this id \"+id+\" is deleted!!";
    }

    @Override
    public Object searchProductByBrand(String brand) {
        List<Product> products = productRepository.findProductByProductBrand(brand);
        if (!products.isEmpty())
            return products;
        else
            throw new RuntimeException("Product Not Found!!");
    }

    @Override
    public String updateProductByName(String name, Product product) {
        if (productRepository.existsProductByProductName(name)){
            productRepository.save(product);
            return "Product details updated.";
        }
        throw new RuntimeException("Product not exist with this product name : "+name);
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = productRepository.getAllProducts();
        if (!products.isEmpty())
            return products;
        else
            return List.of();
    }
}
