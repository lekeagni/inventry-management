package com.example.category_service.service.ServiceImpl;

import com.example.category_service.dto.CategoryDTO;
import com.example.category_service.exception.CategoryAlreadyExistException;
import com.example.category_service.exception.CategoryNotFoundException;
import com.example.category_service.mapper.CategoryMapper;
import com.example.category_service.model.CategoryModel;
import com.example.category_service.repository.CategoryRepository;
import com.example.category_service.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO dto) {
       Optional< CategoryModel> existCategory = this.categoryRepository.findByName(dto.getName());
       if (existCategory.isPresent()){
           throw new CategoryAlreadyExistException(dto.getName());
       }
        CategoryModel categoryModel = categoryMapper.toEntity(dto);
        CategoryModel saved = this.categoryRepository.save(categoryModel);
        return categoryMapper.toDTO(saved);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<CategoryModel> found = this.categoryRepository.findAll();

        return found.stream().map(categoryMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(int categoryId) {
        CategoryModel categoryModel = this.categoryRepository.findById(categoryId)
                .orElseThrow(()->new CategoryNotFoundException(categoryId));
        return categoryMapper.toDTO(categoryModel);
    }

    @Override
    public CategoryDTO updateCategory(int categoryId, CategoryDTO dto) {
        CategoryModel exist = this.categoryRepository.findById(categoryId)
                .orElseThrow(()->new CategoryNotFoundException(categoryId));
        exist.setName(dto.getName());
        Optional<CategoryModel> categoryModel = this.categoryRepository.findByName(dto.getName());
        if (categoryModel.isPresent()){
            throw new CategoryAlreadyExistException(dto.getName());
        }
        CategoryModel cat = this.categoryRepository.save(exist);

        return categoryMapper.toDTO(cat);
    }

    @Override
    public Boolean deleteCategory(int categoryId) {
        Optional<CategoryModel> found = this.categoryRepository.findById(categoryId);
        if (found.isPresent()){
            CategoryModel category = found.get();
            this.categoryRepository.delete(category);
            return true;
        }
        return false;
    }
}
