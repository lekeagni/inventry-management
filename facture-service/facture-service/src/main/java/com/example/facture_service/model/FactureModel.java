package com.example.facture_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "factures")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FactureModel {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int factureId;
    private int orderId;

    @Column(name = "DATE",nullable = false)
    private LocalDateTime date;

    @Column(name = "PRICE",nullable = false)
    private Double price;

    @Column(name = "STATUS",nullable = false)
    private String status;
}
