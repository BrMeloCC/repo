package com.example.program.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.program.entity.Catalog;

public interface CatalogRepository extends JpaRepository<Catalog, String> {

    boolean exexistsByName (String name);
}
