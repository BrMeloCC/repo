package com.example.program.util.validation;

import java.util.function.BooleanSupplier;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.program.dto.UserDto;
import com.example.program.repository.UserRepository;
import com.example.program.util.exception.ResourceAlreadyExistsException;

@Component
public class DuplicateUserValidator {

    @Autowired
    private UserRepository userRepository;

    public void validate(UserDto userDto) {
        Stream.of(
            new ValidationCheck(() -> userRepository.existsByUserCode(userDto.getUserCode().toString()), "UserCode already exists"),
            new ValidationCheck(() -> userRepository.existsByCpf(userDto.getCpf()), "CPF already exists"),
            new ValidationCheck(() -> userRepository.existsByLogin(userDto.getLogin()), "Login already exists"),
            new ValidationCheck(() -> userRepository.existsByEmail(userDto.getEmail()), "Email already exists")
        )
        .filter(ValidationCheck::test)
        .findFirst()
        .ifPresent(check -> {
            throw new ResourceAlreadyExistsException(check.getMessage());
        });
    }

    private static class ValidationCheck {
        private final BooleanSupplier check;
        private final String message;

        ValidationCheck(BooleanSupplier check, String message) {
            this.check = check;
            this.message = message;
        }

        public boolean test() {
            return check.getAsBoolean();
        }

        public String getMessage() {
            return message;
        }
    }
}
