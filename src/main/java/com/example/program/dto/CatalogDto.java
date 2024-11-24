package com.example.program.dto;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CatalogDto {
    private UUID catalogCode = UUID.randomUUID();
    private String name;
    private String description;
    private String category;

    public CatalogDto(UUID catalogCode, String name, String description, String category) {
        this.catalogCode = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.category = category;
    }
}
