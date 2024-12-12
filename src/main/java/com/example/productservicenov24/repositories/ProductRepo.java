package com.example.productservicenov24.repositories;

import com.example.productservicenov24.models.Product;
import com.example.productservicenov24.projections.TitleAndDescription;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query(value = "select p.title,p.description from products p where p.id=:id",nativeQuery = true)
    TitleAndDescription getWithTitleAndDescription(@Param("id") Long id);
    List<Product> findProductsByTitleContains(String searchText, PageRequest pageRequest);
}
