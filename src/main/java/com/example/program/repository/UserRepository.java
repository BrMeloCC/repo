package com.example.program.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.program.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByUserCode(String userCode);

    boolean existsByCpf(String cpf);

    boolean existsByLogin(String login);

    boolean existsByEmail(String email);

}
