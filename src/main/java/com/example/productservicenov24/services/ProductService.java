package com.example.productservicenov24.services;

import com.example.productservicenov24.exceptions.ProductRelatedException;
import com.example.productservicenov24.exceptions.RestTemplateRelatedException;
import com.example.productservicenov24.models.Product;

import java.util.List;

public interface ProductService {
    public Product getProductById(Long id) throws RestTemplateRelatedException;
    public List<Product> getProducts() throws RestTemplateRelatedException, ProductRelatedException;
    public Product addProduct(Product product) throws RestTemplateRelatedException;
    public Product updateProduct(Long id,Product product) throws RestTemplateRelatedException;
    public void deleteProduct(Long id) throws RestTemplateRelatedException;
    public Product replaceProduct(Long id,Product product) throws RestTemplateRelatedException;
}
