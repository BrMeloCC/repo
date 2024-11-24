package com.example.program.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.program.dto.UserDto;
import com.example.program.mapper.UserMapper;
import com.example.program.repository.UserRepository;
import com.example.program.service.UserService;
import com.example.program.util.exception.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> findById(UUID id) {
        String idString = id.toString();
        return Optional.ofNullable(userRepository.findById(idString)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id)));
    }

    @Override
    public UserDto save(UserDto userDto) {
        var user = userMapper.toEntity(userDto);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public void deleteById(UUID id) {
        String idString = id.toString();
        userRepository.deleteById(idString);
    }

    @Override
    public UserDto updateUser(UUID id, UserDto userDto) {
        var existingUser = userRepository.findById(id.toString())
                .orElseThrow(() -> new UserNotFoundException(id));

        BeanUtils.copyProperties(userDto, existingUser, getNullPropertyNames(userDto));

        userRepository.save(existingUser);
        return userMapper.toDto(existingUser);
    }

    private static String[] getNullPropertyNames(UserDto source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        return Arrays.stream(pds)
                .map(java.beans.PropertyDescriptor::getName)
                .filter(name -> src.getPropertyValue(name) == null)
                .toArray(String[]::new);
    }
}
