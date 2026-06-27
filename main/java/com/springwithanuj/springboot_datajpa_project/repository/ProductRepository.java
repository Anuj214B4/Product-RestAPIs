package com.springwithanuj.springboot_datajpa_project.repository;

import com.springwithanuj.springboot_datajpa_project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductByProductBrand(String brand);

    boolean existsProductByProductName(String name);

    //JPQL/HQL
    @Query("SELECT p From Product p")
    List<Product> getAllProducts();
}
