package com.example.productservicenov24.services;

import com.example.productservicenov24.Dtos.FakeProduct;
import com.example.productservicenov24.models.Category;
import com.example.productservicenov24.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Product> getProducts() {
        FakeProduct[] fakeProducts = restTemplate.getForObject("https://fakestoreapi.com/products", FakeProduct[].class);
        //Exception need to be added here.
        List<Product> products = new ArrayList<>();
        for (FakeProduct fakeProduct : fakeProducts) {
            products.add(convertFakeProductToProductDto(fakeProduct));
        }
        return products;
    }

    @Override
    public Product addProduct(Product product) {
        FakeProduct fakeProduct = convertFakeProductToProductDto(product);
        return convertFakeProductToProductDto(restTemplate.postForObject("https://fakestoreapi.com/products", fakeProduct, FakeProduct.class));
    }

    @Override
    public Product updateProduct(Long id,Product product) {
        FakeProduct fakeProduct = convertFakeProductToProductDto(product);
        FakeProduct fakeProduct1 = restTemplate.patchForObject("https://fakestoreapi.com/products/" + id,fakeProduct , FakeProduct.class);
        return convertFakeProductToProductDto(fakeProduct1);
    }

    @Override
    public void deleteProduct(Long id) {
        restTemplate.delete("https://fakestoreapi.com/products/" + id);
    }

    @Override
    public Product replaceProduct(Long id,Product product) {
        FakeProduct fakeProduct = convertFakeProductToProductDto(product);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeProduct);
        HttpMessageConverterExtractor<FakeProduct> responseExtractor = new HttpMessageConverterExtractor(FakeProduct.class, restTemplate.getMessageConverters() );
        FakeProduct fakeProduct1 = restTemplate.execute("http://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor);
        return convertFakeProductToProductDto(fakeProduct1);
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
    public FakeProduct convertFakeProductToProductDto(Product product) {
        FakeProduct fakeProduct = new FakeProduct();
        fakeProduct.setId(product.getId());
        fakeProduct.setTitle(product.getTitle());
        fakeProduct.setDescription(product.getDescription());
        fakeProduct.setPrice(product.getPrice());
        if(product.getCategory()!=null)fakeProduct.setCategory(product.getCategory().getTitle());
        return fakeProduct;
    }

}
