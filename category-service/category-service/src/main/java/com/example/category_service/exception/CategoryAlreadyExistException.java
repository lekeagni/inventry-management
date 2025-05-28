package com.example.category_service.exception;

public class CategoryAlreadyExistException extends CustomException {
    public CategoryAlreadyExistException(String name) {

        super("category already exist with " + name);
    }
}
