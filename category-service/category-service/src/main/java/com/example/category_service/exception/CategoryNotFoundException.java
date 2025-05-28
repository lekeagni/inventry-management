package com.example.category_service.exception;

public class CategoryNotFoundException extends CustomException {
    public CategoryNotFoundException(Integer categoryId) {

        super("category not found with " + categoryId);
    }
}
