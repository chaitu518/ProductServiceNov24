package com.example.productservicenov24.services;

import com.example.productservicenov24.models.Product;

import java.util.List;

public interface ProductService {
    public Product getProductById(Long id);
    public List<Product> getProducts();
    public Product addProduct(Product product);
    public Product updateProduct(Long id,Product product);
    public void deleteProduct(Long id);
    public Product replaceProduct(Long id,Product product);
}
