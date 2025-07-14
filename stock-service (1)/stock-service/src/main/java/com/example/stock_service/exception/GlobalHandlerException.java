package com.example.stock_service.exception;

import com.example.stock_service.model.ErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

public class GlobalHandlerException {

    @ExceptionHandler(StockNotFoundException.class)
    public ResponseEntity<ErrorException> handlerStockNotFoundException(StockNotFoundException exception){
        ErrorException errorException = ErrorException.builder()
                .localDateTime(LocalDateTime.now())
                .httpStatus(HttpStatus.NO_CONTENT.value())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorException);
    }
}
