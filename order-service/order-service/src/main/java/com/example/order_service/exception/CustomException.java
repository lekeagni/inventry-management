package com.example.order_service.exception;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
