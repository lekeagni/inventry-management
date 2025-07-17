package com.example.payment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FactureDTO {

    private int factureId;
    private int orderId;
    private Double price;
    private LocalDateTime date;
}
