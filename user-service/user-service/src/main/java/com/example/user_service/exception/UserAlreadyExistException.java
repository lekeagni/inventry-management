package com.example.user_service.exception;

public class UserAlreadyExistException extends CustomException {
    public UserAlreadyExistException(String username) {

        super("user already exist with " + username);
    }
}
