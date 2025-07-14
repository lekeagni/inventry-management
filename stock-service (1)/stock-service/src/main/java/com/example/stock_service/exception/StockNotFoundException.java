package com.example.stock_service.exception;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(Integer  stockId) {

        super(" Stock not found with" + stockId);
    }
}
