package com.example.facture_service.exception;

public class OrderNotFoundException extends CustomException {
    public OrderNotFoundException(Integer orderId) {

        super("order not found with " + orderId);
    }
}
