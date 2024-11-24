package com.example.program.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.program.dto.UserDto;
import com.example.program.service.UserService;
import com.example.program.util.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieve a list of all users")
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {
        return ApiResponse.buildResponse(userService.findAll(), HttpStatus.OK, "Users retrieved successfully");
    }

    @GetMapping("/{userCode}")
    @Operation(summary = "Get user by ID", description = "Retrieve a user by their unique ID")
    public ResponseEntity<ApiResponse<UserDto>> getUserByUserCode(
            @Parameter(description = "UserCode of the user to be retrieved") @PathVariable String userCode) {
        var userDto = userService.findById(UUID.fromString(userCode)).orElseThrow();
        return ApiResponse.buildResponse(userDto, HttpStatus.OK, "User retrieved successfully");
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Create a new user with the provided details")
    public ResponseEntity<ApiResponse<UserDto>> createUser(@Valid @RequestBody UserDto userDTO) {
        var createdUser = userService.save(userDTO);
        return ApiResponse.buildResponse(createdUser, HttpStatus.CREATED, "User created successfully");
    }

    @PatchMapping("/{userCode}")
    @Operation(summary = "Update an existing user", description = "Update a user with the provided details")
    public ResponseEntity<ApiResponse<UserDto>> updateUser(
            @Parameter(description = "ID of the user to be updated") @PathVariable String userCode,
            @RequestBody UserDto userUpdateBody) {
        var updatedUser = userService.updateUser(UUID.fromString(userCode), userUpdateBody);
        return ApiResponse.buildResponse(updatedUser, HttpStatus.OK, "User updated successfully");
    }

    @DeleteMapping("/{userCode}")
    @Operation(summary = "Delete a user", description = "Delete a user by their unique userCode")
    public ResponseEntity<ApiResponse<Void>> deleteUser(
            @Parameter(description = "ID of the user to be deleted") @PathVariable String userCode) {
        userService.deleteById(UUID.fromString(userCode));
        return ApiResponse.buildResponse(null, HttpStatus.NO_CONTENT, "User deleted successfully");
    }
}
