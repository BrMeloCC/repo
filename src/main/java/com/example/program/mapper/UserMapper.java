package com.example.program.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.program.dto.UserDto;
import com.example.program.entity.User;

@Component
public class UserMapper {

    public UserDto toDto(User entity) {
        UserDto dto = new UserDto();
        dto.setUserCode(UUID.fromString(entity.getUserCode()));
        dto.setName(entity.getName());
        dto.setCpf(entity.getCpf());
        dto.setEmail(entity.getEmail());
        dto.setLogin(entity.getLogin());
        dto.setPassword(entity.getPassword());
        dto.setAdminLevel(entity.getAdminLevel());
        return dto;
    }

    public User toEntity(UserDto dto) {
        User entity = new User();
        entity.setUserCode(dto.getUserCode().toString());
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setEmail(dto.getEmail());
        entity.setLogin(dto.getLogin());
        entity.setPassword(dto.getPassword());
        entity.setAdminLevel(dto.getAdminLevel());
        return entity;
    }
}
