package com.example.program.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.program.dto.UserDto;

public interface UserService {

    public List<UserDto> findAll();

    Optional<UserDto> findById(UUID userCode);

    UserDto save(UserDto userDTO);

    void deleteById(UUID userCode);

    UserDto updateUser(UUID userCode, UserDto userDto);
}
