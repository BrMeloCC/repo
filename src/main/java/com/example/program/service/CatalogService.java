package com.example.program.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.program.dto.CatalogDto;

public interface CatalogService {

    public List<CatalogDto> findAll();

    Optional<CatalogDto> findById(UUID catalogCode);

    CatalogDto save(CatalogDto catalogDto);

    void deleteById(UUID catalogCode);

    CatalogDto updateUser(UUID catalogCode, CatalogDto catalogDto);

}
