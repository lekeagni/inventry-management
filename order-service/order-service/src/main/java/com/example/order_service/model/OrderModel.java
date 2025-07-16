package com.example.order_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private int userId;
    private int productId;
    @Column(name = "QUANTITY", nullable = false)
    private int quantity;
    @Column(name = "DATE",nullable = false)
    private LocalDateTime date;

    @Column(name = "TOTAL_AMOUNT",nullable = false,length = 10)
    private Double total_amount;

    @Column(name = "STATUTS",nullable = false, columnDefinition = "VARCHAR(255) DEFAULT ' en attente' ")
    private String statuts;
}
