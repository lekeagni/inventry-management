package com.example.stock_service.service.ServiceImpl;

import com.example.stock_service.dto.StockDTO;
import com.example.stock_service.mapper.StockMapper;
import com.example.stock_service.repository.StockRepository;
import com.example.stock_service.service.StockService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public StockServiceImpl(StockRepository stockRepository, StockMapper stockMapper) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
    }

    @Override
    public StockDTO createStock(StockDTO dto) {
        return null;
    }

    @Override
    public List<StockDTO> getAllStocks() {
        return List.of();
    }

    @Override
    public StockDTO getStockById(int StockId) {
        return null;
    }

    @Override
    public StockDTO updateStock(int stockId, StockDTO dto) {
        return null;
    }

    @Override
    public Boolean deleteStock(int stockId) {
        return null;
    }
}
