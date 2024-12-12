package com.example.productservicenov24.controllers;

import com.example.productservicenov24.models.Product;
import com.example.productservicenov24.services.ProductService;
import com.example.productservicenov24.services.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }
    @GetMapping
    public List<Product> searchProduct(@RequestParam("keyword") String searchText, @RequestParam(value = "pagesize", defaultValue = "3") int pageSize,@RequestParam(value = "pageno",defaultValue = "0") int pageno) {
        return searchService.searchProduct(searchText,pageSize,pageno);
    }

}
