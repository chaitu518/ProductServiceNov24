package com.example.productservicenov24.repositories;

import com.example.productservicenov24.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    Optional<Category> findByTitle(String title);
}
