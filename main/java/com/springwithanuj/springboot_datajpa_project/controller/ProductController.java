package com.springwithanuj.springboot_datajpa_project.controller;

import com.springwithanuj.springboot_datajpa_project.model.Product;
import com.springwithanuj.springboot_datajpa_project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.saveProduct(product));
    }

    @PostMapping("/products")
    public ResponseEntity<List<Product>> saveMultipleProduct(@RequestBody List<Product> products) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.saveProducts(products));
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.deleteProductById(id));
    }

    @GetMapping("/searchByBrand")
    public ResponseEntity<?> getProductByBrand(@RequestParam String brand){
        try{
            return ResponseEntity.ok(productService.searchProductByBrand(brand));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateProductByName")
    public ResponseEntity<String> updateProductByName(@RequestParam String name,
                                                      @RequestBody Product product){
        try{
            String msg = productService.updateProductByName(name, product);
            return ResponseEntity.ok(msg);
        }catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        }
    }

    @GetMapping("/getAllProds")
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }
}
