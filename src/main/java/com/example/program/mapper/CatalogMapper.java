package com.example.program.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.program.dto.CatalogDto;
import com.example.program.entity.Catalog;

@Component
public class CatalogMapper {

    public CatalogDto toDto (Catalog entity){
        CatalogDto dto = new CatalogDto();
        dto.setCatalogCode(UUID.fromString(entity.getCatalogCode()));
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setCategory(entity.getCategory());
        return dto;
    }

    public Catalog toEntity (CatalogDto dto){
        Catalog entity = new Catalog();
        entity.setCatalogCode(dto.getCatalogCode().toString());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setCategory(dto.getCategory());
        return entity;
    }
}
