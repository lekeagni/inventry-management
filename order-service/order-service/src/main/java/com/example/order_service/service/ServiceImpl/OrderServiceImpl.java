package com.example.order_service.service.ServiceImpl;

import com.example.order_service.dto.OrderDTO;
import com.example.order_service.dto.ProductDTO;
import com.example.order_service.dto.UserDTO;
import com.example.order_service.exception.*;
import com.example.order_service.feign.ProductFeignClient;
import com.example.order_service.feign.UserFeignClient;
import com.example.order_service.mapper.OrderMapper;
import com.example.order_service.model.OrderModel;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.service.OrderService;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserFeignClient userFeignClient;
    private final ProductFeignClient productFeignClient;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, UserFeignClient userFeignClient, ProductFeignClient productFeignClient) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.userFeignClient = userFeignClient;
        this.productFeignClient = productFeignClient;
    }

    @Override
    public OrderDTO createOrder(OrderDTO dto) {
        if (this.orderRepository.existsById(dto.getOrderId())) {
            throw new OrderAlreadyExistException(dto.getOrderId());
        }

        ProductDTO productDTO;

        try {
            productDTO = this.productFeignClient.getProductById(dto.getProductId());
        } catch (FeignException.NotFound e) {
            throw new ProductNotFoundException(dto.getProductId());
        }

        try {
            UserDTO userDTO = this.userFeignClient.getUserById(dto.getUserId());
        } catch (FeignException.NotFound e) {
            throw new UserNotFoundException(dto.getUserId());
        }

        if (productDTO.getQuantity() < dto.getQuantity()){
            throw new InsufficientStockException(dto.getProductId());
        }

        Double totalAmount = productDTO.getPrice() * dto.getQuantity();

        OrderModel orderModel = orderMapper.toEntity(dto);
        orderModel.setProductId(dto.getProductId());
        orderModel.setUserId(dto.getUserId());
        orderModel.setDate(LocalDateTime.now());
        orderModel.setTotal_amount(totalAmount);

        orderModel.setStatuts(" en_attente");

        return orderMapper.toDTO(this.orderRepository.save(orderModel));
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<OrderModel> orderModel = this.orderRepository.findAll();
        if(orderModel.isEmpty()){
            return List.of();
        }
        return orderModel.stream().map(orderMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(int orderId) {

        OrderModel orderModel = this.orderRepository.findById(orderId)
                .orElseThrow(()->new OrderNotFoundException(orderId));
        return orderMapper.toDTO(orderModel);
    }

    @Override
    public OrderDTO updateOrder(int orderId, OrderDTO dto) {

        OrderModel existingOrder = this.orderRepository.findById(orderId)
                .orElseThrow(()->new OrderNotFoundException(orderId));

        ProductDTO productDTO = this.productFeignClient.getProductById(dto.getProductId());
        if (productDTO == null){
            throw new ProductNotFoundException(dto.getProductId());
        }

        UserDTO userDTO = this.userFeignClient.getUserById(dto.getUserId());
        if (userDTO == null){
            throw new UserNotFoundException(dto.getUserId());
        }
// verification de la quantité de stock
        if (productDTO.getQuantity() < dto.getQuantity()){
            throw new InsufficientStockException(dto.getProductId());
        }

        Double totalAmount = productDTO.getPrice() * dto.getQuantity();

        existingOrder.setProductId(dto.getProductId());
        existingOrder.setUserId(dto.getUserId());
        existingOrder.setDate(LocalDateTime.now());
        existingOrder.setQuantity(dto.getQuantity());
        existingOrder.setTotal_amount(totalAmount);

        if (existingOrder.getStatuts() == null || existingOrder.getStatuts().isEmpty()) {
            existingOrder.setStatuts(" en_attente");
        }

        return orderMapper.toDTO(this.orderRepository.save(existingOrder));
    }

    @Override
    public Boolean deleteOrder(int orderId) {
        Optional<OrderModel> exist = this.orderRepository.findById(orderId);
        if (exist.isPresent()){
            OrderModel ord = exist.get();
            this.orderRepository.delete(ord);

            return true;
        }
        return false;
    }
}
