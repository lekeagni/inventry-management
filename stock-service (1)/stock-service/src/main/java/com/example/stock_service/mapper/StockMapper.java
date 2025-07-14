package com.example.stock_service.mapper;

import com.example.stock_service.dto.StockDTO;
import com.example.stock_service.model.StockModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {

    StockModel toEntity(StockDTO dto);

    StockDTO toDTO(StockModel stockModel);
}
