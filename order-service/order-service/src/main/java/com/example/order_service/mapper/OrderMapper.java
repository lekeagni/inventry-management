package com.example.order_service.mapper;

import com.example.order_service.dto.OrderDTO;
import com.example.order_service.model.OrderModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderModel toEntity(OrderDTO dto);

    OrderDTO toDTO(OrderModel orderModel);
}
