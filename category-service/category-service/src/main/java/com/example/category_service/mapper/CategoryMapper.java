package com.example.category_service.mapper;

import com.example.category_service.dto.CategoryDTO;
import com.example.category_service.model.CategoryModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryModel toEntity(CategoryDTO dto);

    CategoryDTO toDTO(CategoryModel categoryModel);
}
