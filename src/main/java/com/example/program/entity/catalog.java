package com.example.program.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name = "catalog")
@Data
public class Catalog {

    @Id
    private String catalogCode;
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    private String description;
    @NotEmpty(message = "Category cannot be empty")
    private String category;
}
