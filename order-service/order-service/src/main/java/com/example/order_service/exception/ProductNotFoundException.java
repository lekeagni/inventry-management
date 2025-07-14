package com.example.order_service.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Integer productId) {

        super("product not found with " + productId);
    }
}
