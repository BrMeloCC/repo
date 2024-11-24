package com.example.program.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.program.dto.UserDto;
import com.example.program.mapper.UserMapper;
import com.example.program.repository.UserRepository;
import com.example.program.service.UserService;
import com.example.program.util.exception.CodeFoundException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<UserDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> findById(UUID userCode) {
        var userCodeString = userCode.toString();
        return Optional.ofNullable(repository.findById(userCodeString)
                .map(mapper::toDto)
                .orElseThrow(() -> new CodeFoundException(userCode)));
    }

    @Override
    public UserDto save(UserDto userDto) {
        var user = mapper.toEntity(userDto);
        user = repository.save(user);
        return mapper.toDto(user);
    }

    @Override
    public void deleteById(UUID userCode) {
        var userCodeString = userCode.toString();
        repository.deleteById(userCodeString);
    }

    @Override
    public UserDto updateUser(UUID userCode, UserDto userDto) {
        var existingUser = repository.findById(userCode.toString())
                .orElseThrow(() -> new CodeFoundException(userCode));
        BeanUtils.copyProperties(userDto, existingUser, getNullPropertyNames(userDto));
        repository.save(existingUser);
        return mapper.toDto(existingUser);
    }

    private static String[] getNullPropertyNames(UserDto source) {
        final var src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        return Arrays.stream(pds)
                .map(java.beans.PropertyDescriptor::getName)
                .filter(name -> src.getPropertyValue(name) == null)
                .toArray(String[]::new);
    }
}
