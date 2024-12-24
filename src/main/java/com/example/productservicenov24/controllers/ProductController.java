package com.example.productservicenov24.controllers;

import com.example.productservicenov24.exceptions.ProductRelatedException;
import com.example.productservicenov24.exceptions.RestTemplateRelatedException;
import com.example.productservicenov24.models.Product;
import com.example.productservicenov24.services.ProductService;
import com.example.productservicenov24.services.TokenService;
import jakarta.servlet.annotation.HttpMethodConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    @Autowired
    private TokenService tokenService;

    public ProductController(@Qualifier("selfproductservice") ProductService productService){

        this.productService = productService;
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) throws RestTemplateRelatedException, ProductRelatedException {
//        @RequestHeader("Token") String token
//        if(!tokenService.validateToken(token)){
//            throw new RestTemplateRelatedException("Token is not valid");
//        }
        return productService.getProductById(id);
    }
    @GetMapping("")
    public List<Product> getAllProducts() throws RestTemplateRelatedException, ProductRelatedException {
        List<Product> products = productService.getProducts();
        return products;
    }
    @PostMapping("")
    public Product addProduct(@RequestBody Product product) throws RestTemplateRelatedException, ProductRelatedException {
        return productService.addProduct(product);
    }
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) throws RestTemplateRelatedException {
        return productService.updateProduct(id, product);
    }
    @PutMapping("/{id}")
    public Product replaceProductById(@PathVariable Long id, @RequestBody Product product) throws RestTemplateRelatedException {
        return productService.replaceProduct(id,product);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) throws RestTemplateRelatedException {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product with id : "+id+" got deleted successfully", HttpStatus.OK);
    }
}
