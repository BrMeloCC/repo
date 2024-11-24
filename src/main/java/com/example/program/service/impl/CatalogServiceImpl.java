package com.example.program.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.program.dto.CatalogDto;
import com.example.program.mapper.CatalogMapper;
import com.example.program.repository.CatalogRepository;
import com.example.program.service.CatalogService;
import com.example.program.util.exception.CodeFoundException;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository repository;
    private final CatalogMapper mapper;

    @Autowired
    public CatalogServiceImpl(CatalogRepository repository, CatalogMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<CatalogDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CatalogDto> findById(UUID catalogCode) {
        var catalogCodeString = catalogCode.toString();
        return Optional.ofNullable(repository.findById(catalogCodeString)
        .map(mapper::toDto)
        .orElseThrow(() -> new CodeFoundException(catalogCode)));
    }

    @Override
    public CatalogDto save(CatalogDto catalogDto) {
        var catalog = mapper.toEntity(catalogDto);
        catalog = repository.save(catalog);
        return mapper.toDto(catalog);
    }

    @Override
    public void deleteById(UUID catalogCode) {
        var catalogCodeString = catalogCode.toString();
        repository.deleteById(catalogCodeString);
    }

    @Override
    public CatalogDto updateUser(UUID catalogCode, CatalogDto catalogDto) {
        var existingCatalog = repository.findById(catalogCode.toString())
                .orElseThrow(() -> new CodeFoundException(catalogCode));
        BeanUtils.copyProperties(catalogDto, existingCatalog, getNullPropertyNames(catalogDto));
        repository.save(existingCatalog);
        return mapper.toDto(existingCatalog);
    }

        private static String[] getNullPropertyNames(CatalogDto source) {
        final var src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        return Arrays.stream(pds)
                .map(java.beans.PropertyDescriptor::getName)
                .filter(name -> src.getPropertyValue(name) == null)
                .toArray(String[]::new);
    }
}
