package com.example.productservicenov24.services;

import com.example.productservicenov24.Dtos.FakeProduct;
import com.example.productservicenov24.models.Category;
import com.example.productservicenov24.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService {
    RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public Product getProductById(Long id) {
        FakeProduct fakeProduct = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeProduct.class);
        return convertFakeProductToProductDto(fakeProduct);
    }
    public Product convertFakeProductToProductDto(FakeProduct fakeProduct) {
        Product product = new Product();
        product.setId(fakeProduct.getId());
        product.setTitle(fakeProduct.getTitle());
        product.setDescription(fakeProduct.getDescription());
        product.setPrice(fakeProduct.getPrice());
        Category category = new Category();
        category.setTitle(fakeProduct.getCategory());
        product.setCategory(category);
        return product;
    }

}
