package com.example.stock_service.exception;

public class StockAlreadyExistException extends RuntimeException {
    public StockAlreadyExistException(String message) {
        super(message);
    }
}
