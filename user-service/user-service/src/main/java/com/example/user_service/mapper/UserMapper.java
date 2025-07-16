package com.example.user_service.mapper;

import com.example.user_service.dto.UserDTO;
import com.example.user_service.model.UserModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserModel toEntity(UserDTO dto);

    UserDTO toDTO(UserModel userModel);

}
