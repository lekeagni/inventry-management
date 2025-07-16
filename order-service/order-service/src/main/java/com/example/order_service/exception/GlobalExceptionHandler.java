package com.example.order_service.exception;

import com.example.order_service.model.ErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorException>handlerOrderNotFoundException(OrderNotFoundException exception){
        ErrorException errorException = ErrorException.builder()
                .localDateTime(LocalDateTime.now())
                .httpStatus(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorException);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorException>handlerProductNotFoundException(ProductNotFoundException exception){
        ErrorException errorException = ErrorException.builder()
                .localDateTime(LocalDateTime.now())
                .httpStatus(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorException);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorException>handlerUserNotFoundException(UserNotFoundException exception){
        ErrorException errorException = ErrorException.builder()
                .localDateTime(LocalDateTime.now())
                .httpStatus(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorException);
    }

    @ExceptionHandler(OrderAlreadyExistException.class)
    public ResponseEntity<ErrorException>handlerOrderAlreadyExistException(OrderAlreadyExistException exception){
        ErrorException errorException = ErrorException.builder()
                .localDateTime(LocalDateTime.now())
                .httpStatus(HttpStatus.CONFLICT.value())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorException);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ErrorException>handlerInsufficientStockException(InsufficientStockException exception){
        ErrorException errorException = ErrorException.builder()
                .localDateTime(LocalDateTime.now())
                .httpStatus(HttpStatus.CONFLICT.value())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorException);
    }
}
