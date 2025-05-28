package com.example.category_service.repository;

import com.example.category_service.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryModel,Integer> {
    Optional<CategoryModel> findByName(String name);
}
