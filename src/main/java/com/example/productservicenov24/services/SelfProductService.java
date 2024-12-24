package com.example.productservicenov24.services;

import com.example.productservicenov24.exceptions.ProductRelatedException;
import com.example.productservicenov24.exceptions.RestTemplateRelatedException;
import com.example.productservicenov24.models.Category;
import com.example.productservicenov24.models.Product;
import com.example.productservicenov24.projections.TitleAndDescription;
import com.example.productservicenov24.repositories.CategoryRepo;
import com.example.productservicenov24.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service("selfproductservice")
public class SelfProductService implements ProductService {
    CategoryRepo repo;
    ProductRepo productRepo;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    public SelfProductService(CategoryRepo repo,ProductRepo productRepo) {
        this.repo = repo;
        this.productRepo = productRepo;
    }
    @Override
    public Product getProductById(Long id) throws RestTemplateRelatedException, ProductRelatedException {
        Product redproudct = (Product) redisTemplate.opsForHash().get("PRODUCTS","PRODUCT_"+id);
        if (redproudct != null) {
            //hit
            return redproudct;
        }
        Optional<Product> product = productRepo.findById(id);
        //TitleAndDescription td = productRepo.getWithTitleAndDescription(id);
        //System.out.println(td.getTitle()+" "+td.getDescription());
        if (product.isPresent()) {
            redisTemplate.opsForHash().put("PRODUCTS","PRODUCT_"+id,product.get());
            return product.get();
        }
        throw new ProductRelatedException("Product with Id "+id+" not Found");
        //return null;
    }

    @Override
    public List<Product> getProducts() throws RestTemplateRelatedException, ProductRelatedException {
        List<Product> products = productRepo.findAll();
        if (products==null) {
            throw new ProductRelatedException("No products found");
        }
        return products;
    }

    @Override
    public Product addProduct(Product product) throws RestTemplateRelatedException, ProductRelatedException {
        Category category = product.getCategory();
        Product product1 =null;
        if(category==null || category.getTitle()==null) {
            throw new ProductRelatedException("Category is null");
        }
        else if(category.getId()==null){
            //nothing
        }
        else{
            Optional<Category> categoryOptional=repo.findById(category.getId());
            if(categoryOptional.isPresent()) {
                category = categoryOptional.get();
            }
            else {
                category.setId(null);
            }
        }
        product.setCategory(category);
        product1= productRepo.save(product);
        return product1;

    }

    @Override
    public Product updateProduct(Long id, Product product) throws RestTemplateRelatedException {
        return null;
    }

    @Override
    public void deleteProduct(Long id) throws RestTemplateRelatedException {
        productRepo.deleteById(id);
    }

    @Override
    public Product replaceProduct(Long id, Product product) throws RestTemplateRelatedException {
        return null;
    }
}
