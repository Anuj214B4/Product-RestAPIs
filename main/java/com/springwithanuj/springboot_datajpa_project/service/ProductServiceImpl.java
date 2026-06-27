package com.springwithanuj.springboot_datajpa_project.service;

import com.springwithanuj.springboot_datajpa_project.dtos.PaginatedResponse;
import com.springwithanuj.springboot_datajpa_project.dtos.ProductRequest;
import com.springwithanuj.springboot_datajpa_project.dtos.ProductResponse;
import com.springwithanuj.springboot_datajpa_project.model.Product;
import com.springwithanuj.springboot_datajpa_project.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product saveProduct(ProductRequest productRequest) {
        Product saveToDb = mapToEntity(productRequest);
        return productRepository.save(saveToDb);
    }

    @Override
    public List<Product> saveProducts(List<ProductRequest> productRequests) {
        List<Product> products = productRequests.stream()
                .map(this::mapToEntity)
                .toList();
        return productRepository.saveAllAndFlush(products);
    }

    @Override
    public PaginatedResponse<ProductResponse> getAllProducts(Integer pageNo, Integer pageSize, String sortBy, String sortOrder) {

        Sort sortByOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNo,pageSize, sortByOrder);

        Page<Product> productPage = productRepository.findAll(pageDetails);

        List<ProductResponse> products = productPage.getContent()
                .stream()
                .map(this::mapToResponse)
                .toList();

        return PaginatedResponse.<ProductResponse>builder()
                .content(products)
                .pageNo(productPage.getNumber())
                .pageSize(productPage.getSize())
                .totalElements(productPage.getTotalElements())
                .totalPages(productPage.getTotalPages())
                .lastPage(productPage.isLast())
                .build();

    }



    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product with this id " + id + " is not found!!"));
    }

    @Override
    public String deleteProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product with this id " + id + " is not found!!"));
        productRepository.delete(product);
        return "Product with this id \"+id+\" is deleted!!";
    }

    @Override
    public Object searchProductByBrand(String brand) {
        List<Product> products = productRepository.findProductByProductBrand(brand);
        if (!products.isEmpty()) return products;
        else throw new RuntimeException("Product Not Found!!");
    }

    @Override
    public String updateProductByName(String name, Product product) {
        if (productRepository.existsProductByProductName(name)) {
            productRepository.save(product);
            return "Product details updated.";
        }
        throw new RuntimeException("Product not exist with this product name : " + name);
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = productRepository.getAllProducts();
        if (!products.isEmpty()) return products;
        else return List.of();
    }


    private Product mapToEntity(ProductRequest request) {
        return Product.builder()
                .productName(request.getProductName())
                .productPrice(request.getProductPrice())
                .productBrand(request.getProductBrand())
                .description(request.getDescription())
                .productCategory(request.getProductCategory())
                .stock(request.getStock())
                .build();
    }

    private ProductResponse mapToResponse(Product product){
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productBrand(product.getProductBrand())
                .productName(product.getProductName())
                .description(product.getDescription())
                .productPrice(product.getProductPrice())
                .productCategory(product.getProductCategory())
                .stock(product.getStock())
                .build();
    }


}
