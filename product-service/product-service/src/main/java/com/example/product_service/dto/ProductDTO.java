package com.example.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private int productId;
    private int categoryId;
    private int userId;
    private String name;
    private String description;
    private Double price;
    private int quantity;
}
