package com.example.product_service.mapper;

import com.example.product_service.dto.ProductDTO;
import com.example.product_service.model.ProductModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-28T13:32:31+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductModel toEntity(ProductDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ProductModel productModel = new ProductModel();

        productModel.setProductId( dto.getProductId() );
        productModel.setUserId( dto.getUserId() );
        productModel.setCategoryId( dto.getCategoryId() );
        productModel.setName( dto.getName() );
        productModel.setDescription( dto.getDescription() );
        productModel.setPrice( dto.getPrice() );
        productModel.setQuantity( dto.getQuantity() );

        return productModel;
    }

    @Override
    public ProductDTO toDTO(ProductModel productModel) {
        if ( productModel == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setProductId( productModel.getProductId() );
        productDTO.setCategoryId( productModel.getCategoryId() );
        productDTO.setUserId( productModel.getUserId() );
        productDTO.setName( productModel.getName() );
        productDTO.setDescription( productModel.getDescription() );
        productDTO.setPrice( productModel.getPrice() );
        productDTO.setQuantity( productModel.getQuantity() );

        return productDTO;
    }
}
