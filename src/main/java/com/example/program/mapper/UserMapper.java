package com.example.program.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.program.dto.UserDto;
import com.example.program.entity.User;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setUserCode(UUID.fromString(user.getUserCode()));
        dto.setName(user.getName());
        dto.setCpf(user.getCpf());
        dto.setEmail(user.getEmail());
        dto.setLogin(user.getLogin());
        dto.setPassword(user.getPassword());
        dto.setAdminLevel(user.getAdminLevel());
        return dto;
    }

    public User toEntity(UserDto dto) {
        User user = new User();
        user.setUserCode(dto.getUserCode().toString());
        user.setName(dto.getName());
        user.setCpf(dto.getCpf());
        user.setEmail(dto.getEmail());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setAdminLevel(dto.getAdminLevel());
        return user;
    }
}
