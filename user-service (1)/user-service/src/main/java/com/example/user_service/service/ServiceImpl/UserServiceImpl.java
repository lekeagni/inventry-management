package com.example.user_service.service.ServiceImpl;

import com.example.user_service.dto.UserDTO;
import com.example.user_service.exception.UserAlreadyExistException;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.mapper.UserMapper;
import com.example.user_service.model.UserModel;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO createUser(UserDTO dto) {
        Optional<UserModel> existingUser = this.userRepository.findByUsername(dto.getUsername());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistException(dto.getUsername());
        }

        UserModel userModel = userMapper.toEntity(dto);
        UserModel savedUser = userRepository.save(userModel);

        return userMapper.toDTO(savedUser);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserModel> found = this.userRepository.findAll();
        return found.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(int userId) {
        UserModel userModel = this.userRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundException(userId));
        return userMapper.toDTO(userModel);
    }

    @Override
    public UserDTO updateUser(int userId, UserDTO dto) {
       UserModel exist = this.userRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundException(userId));

       exist.setUsername(dto.getUsername());
       exist.setEmail(dto.getEmail());
       exist.setPhone(dto.getPhone());

       UserModel user = this.userRepository.save(exist);
       return userMapper.toDTO(user);
    }

    @Override
    public Boolean deleteUser(int userId) {
        Optional<UserModel> exist = this.userRepository.findById(userId);
        if (exist.isPresent()){
            UserModel userModel = exist.get();
            this.userRepository.delete(userModel);
            return true;
        }
        return false;
    }
}
