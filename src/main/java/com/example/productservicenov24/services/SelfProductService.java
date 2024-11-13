package com.example.productservicenov24.services;

import com.example.productservicenov24.exceptions.ProductRelatedException;
import com.example.productservicenov24.exceptions.RestTemplateRelatedException;
import com.example.productservicenov24.models.Category;
import com.example.productservicenov24.models.Product;
import com.example.productservicenov24.projections.TitleAndDescription;
import com.example.productservicenov24.repositories.CategoryRepo;
import com.example.productservicenov24.repositories.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service("selfproductservice")
public class SelfProductService implements ProductService {
    CategoryRepo repo;
    ProductRepo productRepo;
    public SelfProductService(CategoryRepo repo,ProductRepo productRepo) {
        this.repo = repo;
        this.productRepo = productRepo;
    }
    @Override
    public Product getProductById(Long id) throws RestTemplateRelatedException, ProductRelatedException {
        Optional<Product> product = productRepo.findById(id);
        //TitleAndDescription td = productRepo.getWithTitleAndDescription(id);
        //System.out.println(td.getTitle()+" "+td.getDescription());
        if (product.isPresent()) {
            return product.get();
        }
        throw new ProductRelatedException("Product with Id "+id+" not Found");
        //return null;
    }

    @Override
    public List<Product> getProducts() throws RestTemplateRelatedException, ProductRelatedException {
        List<Product> products = productRepo.findAll();
        return products;
    }

    @Override
    public Product addProduct(Product product) throws RestTemplateRelatedException, ProductRelatedException {
        Category category = product.getCategory();
        if (category == null) {
            throw new ProductRelatedException("Category is null");
        }
        else if (category.getTitle() == null ) {
            throw new ProductRelatedException("Category title is null");
        }
        else if(category.getId()==null){
            Optional<Category> categoryOptional = repo.findByTitle(category.getTitle());
            if (categoryOptional.isEmpty()) {
                Category newCategory = new Category();
                newCategory.setTitle(category.getTitle());
                category = repo.save(newCategory);
            }
            else{
                category = categoryOptional.get();
            }
        }
        else{
            Optional<Category> categoryOptional=repo.findById(category.getId());
            if (categoryOptional.isEmpty()) {
                Category newCategory = new Category();
                newCategory.setTitle(category.getTitle());
                category = repo.save(newCategory);
            }
            category = categoryOptional.get();

        }
        product.setCategory(category);
        return productRepo.save(product);

    }

    @Override
    public Product updateProduct(Long id, Product product) throws RestTemplateRelatedException {
        return null;
    }

    @Override
    public void deleteProduct(Long id) throws RestTemplateRelatedException {

    }

    @Override
    public Product replaceProduct(Long id, Product product) throws RestTemplateRelatedException {
        return null;
    }
}
