// package com.example.program.entity;

// import java.util.UUID;

// import javax.xml.catalog.Catalog;

// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.Table;
// import lombok.Data;
// import lombok.Getter;
// import lombok.Setter;

// @Entity
// @Table(name = "stock")
// @Data
// @Getter
// @Setter
// public class Stock {

//     @Id
//     private UUID stockCode;
//     @ManyToOne
//     @JoinColumn(name = "catalog_code", nullable = false)
//     private Catalog catalog;
//     private Double purchasePrice;
//     private Double salePrice;
//     private int amount;
// }
