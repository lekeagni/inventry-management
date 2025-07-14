package com.example.order_service.exception;

public class OrderAlreadyExistException extends RuntimeException {
    public OrderAlreadyExistException(Integer orderId) {

        super("order already exist by " + orderId);
    }
}
