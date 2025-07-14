package com.example.category_service.mapper;

import com.example.category_service.dto.CategoryDTO;
import com.example.category_service.model.CategoryModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-14T13:21:57+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryModel toEntity(CategoryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CategoryModel categoryModel = new CategoryModel();

        if ( dto.getCategoryId() != null ) {
            categoryModel.setCategoryId( dto.getCategoryId() );
        }
        categoryModel.setName( dto.getName() );

        return categoryModel;
    }

    @Override
    public CategoryDTO toDTO(CategoryModel categoryModel) {
        if ( categoryModel == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setCategoryId( categoryModel.getCategoryId() );
        categoryDTO.setName( categoryModel.getName() );

        return categoryDTO;
    }
}
