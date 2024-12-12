package com.example.productservicenov24.services;

import com.example.productservicenov24.models.Product;
import com.example.productservicenov24.repositories.ProductRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class SearchService {
    private ProductRepo productRepo;
    public SearchService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    public List<Product> searchProduct(String searchText,int pageSize,int pageNo) {
        return productRepo.findProductsByTitleContains(searchText, PageRequest.of(pageNo,pageSize, Sort.by(Sort.Order.desc("price"))));
    }
}
