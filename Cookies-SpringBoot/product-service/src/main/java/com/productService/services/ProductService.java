package com.productService.services;

import com.productService.dto.ProductRequest;
import com.productService.dto.ProductResponse;
import com.productService.model.Product;
import com.productService.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    @PersistenceContext
    private EntityManager entityManager;

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice((productRequest.getPrice()));
        product.setAvailableQuantity(productRequest.getAvailableQuantity());
        product.setImage(productRequest.getImage());
        entityManager.persist(product);
        productRepository.save(product);
    }

    public List<ProductResponse> getAllProdicts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this:: mapToProductResponse).collect(Collectors.toList());
    }

    @Transactional
    private ProductResponse mapToProductResponse(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice((product.getPrice()));
        productResponse.setAvailableQuantity(product.getAvailableQuantity());
        productResponse.setImage(product.getImage());
        entityManager.persist(product);
        return productResponse;
    }

}
