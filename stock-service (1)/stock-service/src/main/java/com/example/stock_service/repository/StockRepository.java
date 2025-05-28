package com.example.stock_service.repository;

import com.example.stock_service.model.StockModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockModel, Integer> {
}
