package com.example.program.util.validation;

import java.util.UUID;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.program.dto.UserDto;

@Aspect
@Component
public class ValidationAspect {

    @Autowired
    private DuplicateUserValidator duplicateUserValidator;

    @Before("execution(* com.example.program.service.UserService.save(..)) && args(userDto)")
    public void validateBeforeSave(UserDto userDto) {
        duplicateUserValidator.validate(userDto);
    }

    @Before("execution(* com.example.program.service.UserService.updateUser(..)) && args(userCode, userDto)")
    public void validateBeforeUpdate(UUID userCode, UserDto userDto) {
        duplicateUserValidator.validate(userDto);
    }
}
