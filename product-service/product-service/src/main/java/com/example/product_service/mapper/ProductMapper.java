package com.example.product_service.mapper;

import com.example.product_service.dto.ProductDTO;
import com.example.product_service.model.ProductModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductModel toEntity(ProductDTO dto);

    ProductDTO toDTO(ProductModel productModel);
}
