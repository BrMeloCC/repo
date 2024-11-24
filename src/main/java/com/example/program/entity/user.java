package com.example.program.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    private String userCode;
    private String name;
    private String cpf;
    private String email;
    private String login;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
    @NotNull(message = "Admin level cannot be null")
    private int adminLevel;
}
