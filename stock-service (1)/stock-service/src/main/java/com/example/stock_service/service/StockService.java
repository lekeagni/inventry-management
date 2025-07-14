package com.example.stock_service.service;

import com.example.stock_service.dto.StockDTO;

import java.util.List;

public interface StockService {

    public StockDTO createStock(StockDTO dto);

    public List<StockDTO> getAllStocks();

    public StockDTO getStockById(int StockId);

    public StockDTO updateStock(int stockId, StockDTO dto);

    public Boolean deleteStock (int stockId);
}
