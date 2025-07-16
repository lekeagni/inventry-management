package com.example.user_service.service;

import com.example.user_service.dto.UserDTO;

import java.util.List;

public interface UserService {

    public UserDTO createUser(UserDTO dto);

    public List<UserDTO> getAllUsers();

    public UserDTO getUserById(int userId);

    public UserDTO updateUser( int userId, UserDTO dto);

    public Boolean deleteUser(int userId);
}
