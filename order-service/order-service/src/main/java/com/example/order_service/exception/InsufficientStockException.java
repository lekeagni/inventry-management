package com.example.order_service.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(Integer productId ) {

        super("Stock insuffisant pour le produit " + productId);
    }
}
