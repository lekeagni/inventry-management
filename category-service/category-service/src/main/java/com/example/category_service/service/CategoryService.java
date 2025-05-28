package com.example.category_service.service;

import com.example.category_service.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    public CategoryDTO createCategory(CategoryDTO dto);

    public List<CategoryDTO> getAllCategories();

    public CategoryDTO getCategoryById(int categoryId);

    public CategoryDTO updateCategory(int categoryId, CategoryDTO dto);

    public Boolean deleteCategory(int categoryId);
}
