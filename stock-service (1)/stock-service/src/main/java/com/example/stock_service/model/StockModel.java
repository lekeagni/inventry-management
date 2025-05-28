package com.example.stock_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stocks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StockModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stockId;
    private int productId;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

    @Column(name = "TYPE", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'entrée' ")
    private String type;
}
