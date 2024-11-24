package com.example.program.dto;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private UUID userCode = UUID.randomUUID();
    private String name;
    private String cpf;
    private String email;
    private String login;
    private String password;
    private int adminLevel;

    public UserDto(String name, String cpf, String email, String login, String password, int adminLevel) {
        this.userCode = UUID.randomUUID();
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.login = login;
        this.password = password;
        this.adminLevel = adminLevel;
    }
}
