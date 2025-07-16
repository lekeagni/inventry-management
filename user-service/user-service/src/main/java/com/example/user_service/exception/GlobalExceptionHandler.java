package com.example.user_service.exception;

import com.example.user_service.model.ErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorException> handlerUserNotFoundException(UserNotFoundException exception){
        ErrorException errorException = ErrorException.builder()
                .localDateTime(LocalDateTime.now())
                .httpStatus(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorException);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorException> handlerUserAlreadyExistException(UserAlreadyExistException exception){
        ErrorException errorException = ErrorException.builder()
                .localDateTime(LocalDateTime.now())
                .httpStatus(HttpStatus.CONFLICT.value())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorException);
    }
}
