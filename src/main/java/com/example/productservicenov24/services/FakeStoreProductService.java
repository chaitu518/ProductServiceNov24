package com.example.productservicenov24.services;

import com.example.productservicenov24.Dtos.FakeProduct;
import com.example.productservicenov24.exceptions.ProductRelatedException;
import com.example.productservicenov24.exceptions.RestTemplateRelatedException;
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
    public Product getProductById(Long id) throws RestTemplateRelatedException {
        try {
            FakeProduct fakeProduct = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeProduct.class);
            return convertFakeProductToProductDto(fakeProduct);
        }
        catch (Exception e) {
            throw new RestTemplateRelatedException("Not able get product with id : "+id);
        }

    }

    @Override
    public List<Product> getProducts() throws RestTemplateRelatedException, ProductRelatedException {
        FakeProduct[] fakeProducts;
        try {
            fakeProducts = restTemplate.getForObject("https://fakestoreapi.com/products", FakeProduct[].class);
        }
        catch (Exception e) {
            throw new RestTemplateRelatedException("Not able get all products");
        }
        if (fakeProducts==null){
            throw new ProductRelatedException("Not able to retrieve products");
        }

        List<Product> products = new ArrayList<>();
        for (FakeProduct fakeProduct : fakeProducts) {
            products.add(convertFakeProductToProductDto(fakeProduct));
        }
        return products;
    }

    @Override
    public Product addProduct(Product product) throws RestTemplateRelatedException {
        FakeProduct fakeProduct;
        try {
            fakeProduct = convertFakeProductToProductDto(product);
            return convertFakeProductToProductDto(restTemplate.postForObject("https://fakestoreapi.com/products", fakeProduct, FakeProduct.class));
        }
        catch (Exception e) {
            throw new RestTemplateRelatedException("Product is not created");
        }
    }

    @Override
    public Product updateProduct(Long id,Product product) throws RestTemplateRelatedException {
        FakeProduct fakeProduct = convertFakeProductToProductDto(product);
        FakeProduct fakeProduct1;
        try {
            fakeProduct1 = restTemplate.patchForObject("https://fakestoreapi.com/products/" + id, fakeProduct, FakeProduct.class);
        }
        catch (Exception e) {
            throw new RestTemplateRelatedException("Not able update the product with product Id : "+id);
        }
        return convertFakeProductToProductDto(fakeProduct1);
    }

    @Override
    public void deleteProduct(Long id) throws RestTemplateRelatedException {
        try{
            restTemplate.delete("https://fakestoreapi.com/products/" + id);
        }
        catch (Exception e) {
            throw new RestTemplateRelatedException("Not able delete the product");
        }
    }

    @Override
    public Product replaceProduct(Long id,Product product) throws RestTemplateRelatedException {
        try {
            FakeProduct fakeProduct = convertFakeProductToProductDto(product);
            RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeProduct);
            HttpMessageConverterExtractor<FakeProduct> responseExtractor = new HttpMessageConverterExtractor(FakeProduct.class, restTemplate.getMessageConverters());
            FakeProduct fakeProduct1 = restTemplate.execute("http://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
            return convertFakeProductToProductDto(fakeProduct1);
        }
        catch (Exception e) {
            throw new RestTemplateRelatedException("Not able replace the product with product Id : "+id);
        }
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
