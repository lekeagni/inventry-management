package com.example.order_service.service;

import com.example.order_service.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    public OrderDTO createOrder(OrderDTO dto);

    public List<OrderDTO> getAllOrders();

    public OrderDTO getOrderById(int orderId);

    public OrderDTO updateOrder(int orderId, OrderDTO dto);

    public Boolean deleteOrder(int orderId);
}
