package com.example.productservicenov24.controllers;

import com.example.productservicenov24.models.Product;
import com.example.productservicenov24.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService){

        this.productService = productService;
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {

        return productService.getProductById(id);
    }
    @GetMapping("")
    public List<Product> getAllProducts() {
        List<Product> products = productService.getProducts();
        return products;
    }
    @PostMapping("")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }
    @PutMapping("/{id}")
    public Product replaceProductById(@PathVariable Long id, @RequestBody Product product) {
        return productService.replaceProduct(id,product);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product with id : "+id+" got deleted successfully", HttpStatus.OK);
    }
}
