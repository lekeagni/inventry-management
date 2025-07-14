package com.example.stock_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StockDTO {

    private int stockId;
    private int productId;
    private int quantity;
    private String type;
}
