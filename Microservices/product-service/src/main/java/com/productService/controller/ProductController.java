package com.productService.controller;

import com.productService.dto.ProductRequest;
import com.productService.dto.ProductResponse;
import com.productService.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private RestTemplate restTemplate;
    @PostMapping("/createProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
         productService.createProduct(productRequest);
    }
    @GetMapping("/getAllProducts")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }
    @PostMapping("/addToCart/{userId}")
    public ResponseEntity<String> addToCart(@PathVariable int userId , @RequestBody ProductRequest productRequest){
        ResponseEntity<String> response = restTemplate.postForEntity("http://cart-service/api/cart/addToCart",productRequest,String.class);
        return response;
    }
    @PostMapping("/addToWishlist/{userId}")
    public ResponseEntity<String> addToWishlist(@PathVariable int userId , @RequestBody ProductRequest productRequest){
        ResponseEntity<String> response = restTemplate.postForEntity("http://wishlist-service/api/wishlist/addItemToWishlist",productRequest,String.class);
        return response;
    }
}
