package com.example.user_service.serviceTest;

import com.example.user_service.dto.UserDTO;
import com.example.user_service.exception.UserAlreadyExistException;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.mapper.UserMapper;
import com.example.user_service.model.UserModel;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.service.ServiceImpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private UserModel userModel;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userModel = new UserModel(1, "johndoe", "john@example.com", "123456789");
        userDTO = new UserDTO(1, "johndoe", "john@example.com", "123456789");
    }

    @Test
    @DisplayName("Créer un utilisateur avec succès")
    void shouldCreateUserSuccessfully() {
        when(userRepository.findByUsername(userDTO.getUsername())).thenReturn(Optional.empty());
        when(userMapper.toEntity(userDTO)).thenReturn(userModel);
        when(userRepository.save(userModel)).thenReturn(userModel);
        when(userMapper.toDTO(userModel)).thenReturn(userDTO);

        UserDTO result = userService.createUser(userDTO);

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("johndoe");
        verify(userRepository, times(1)).save(userModel);
    }

    @Test
    @DisplayName("Échec de la création : utilisateur existe déjà")
    void shouldThrowExceptionIfUserAlreadyExists() {
        when(userRepository.findByUsername(userDTO.getUsername())).thenReturn(Optional.of(userModel));

        assertThatThrownBy(() -> userService.createUser(userDTO))
                .isInstanceOf(UserAlreadyExistException.class)
                .hasMessageContaining("johndoe");

        verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("Récupérer tous les utilisateurs")
    void shouldReturnAllUsers() {
        List<UserModel> models = Arrays.asList(userModel);

        when(userRepository.findAll()).thenReturn(models);
        when(userMapper.toDTO(userModel)).thenReturn(userDTO);

        List<UserDTO> result = userService.getAllUsers();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getUsername()).isEqualTo("johndoe");
    }

    @Test
    @DisplayName("Récupérer un utilisateur par ID")
    void shouldGetUserById() {
        when(userRepository.findById(1)).thenReturn(Optional.of(userModel));
        when(userMapper.toDTO(userModel)).thenReturn(userDTO);

        UserDTO result = userService.getUserById(1);

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("john@example.com");
    }

    @Test
    @DisplayName("Échec de récupération : utilisateur introuvable")
    void shouldThrowExceptionIfUserNotFound() {
        when(userRepository.findById(2)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserById(2))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("2");
    }

    @Test
    @DisplayName("Mettre à jour un utilisateur")
    void shouldUpdateUser() {
        when(userRepository.findById(1)).thenReturn(Optional.of(userModel));
        when(userRepository.save(any(UserModel.class))).thenReturn(userModel);
        when(userMapper.toDTO(userModel)).thenReturn(userDTO);

        UserDTO result = userService.updateUser(1, userDTO);

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("johndoe");
    }

    @Test
    @DisplayName("Échec de mise à jour : utilisateur introuvable")
    void shouldThrowExceptionWhenUpdatingNonExistingUser() {
        when(userRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.updateUser(99, userDTO))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("99");

        verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("Supprimer un utilisateur existant")
    void shouldDeleteUser() {
        when(userRepository.findById(1)).thenReturn(Optional.of(userModel));

        Boolean result = userService.deleteUser(1);

        assertThat(result).isTrue();
        verify(userRepository).delete(userModel);
    }

    @Test
    @DisplayName("Supprimer un utilisateur inexistant")
    void shouldNotDeleteNonExistingUser() {
        when(userRepository.findById(2)).thenReturn(Optional.empty());

        Boolean result = userService.deleteUser(2);

        assertThat(result).isFalse();
        verify(userRepository, never()).delete(any());
    }
}

