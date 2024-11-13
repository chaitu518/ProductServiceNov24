package com.example.productservicenov24.repositories;

import com.example.productservicenov24.models.Product;
import com.example.productservicenov24.projections.TitleAndDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query(value = "select p.title,p.description from products p where p.id=:id",nativeQuery = true)
    TitleAndDescription getWithTitleAndDescription(@Param("id") Long id);
}
