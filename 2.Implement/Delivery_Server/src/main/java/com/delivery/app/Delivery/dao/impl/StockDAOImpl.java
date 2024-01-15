package com.delivery.app.Delivery.dao.impl;

import com.delivery.app.Delivery.dao.StockDAO;
import com.delivery.app.Delivery.data.dto.request.StockIdDto;
import com.delivery.app.Delivery.data.dto.request.stock.StockDto;
import com.delivery.app.Delivery.data.dto.response.review.GetMarketReviewsResponseDto;
import com.delivery.app.Delivery.data.dto.response.stock.StockDetailResponseDto;
import com.delivery.app.Delivery.data.entity.Stock;
import com.delivery.app.Delivery.repository.stock.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StockDAOImpl implements StockDAO {
    private final StockRepository stockRepository;

    @Autowired
    public StockDAOImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public StockDetailResponseDto getDetail(StockIdDto stockIdDto) {
        StockDetailResponseDto result = new StockDetailResponseDto();
        Long stockId = stockIdDto.getStockId();

        Optional<Stock> selected = stockRepository.findById(stockId);

        if(selected.isPresent()) {
            Stock stock = selected.get();
            result.set(
                    stock.getId(),
                    stock.getName(),
                    stock.getPrice(),
                    stock.getReleasedDate(),
                    stock.getManufacturer(),
                    stock.getWeight()
            );
        }

        return result;
    }

    @Override
    public Stock getStock(Long stockId) {
        Optional<Stock> selected = stockRepository.findById(stockId);
        if(selected.isPresent()) {
            return selected.get();
        } else {
            Exception e = new Exception("Not Found");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean add(StockDto stockDto) {

        Stock newStock = new Stock(
                stockDto.getStockId(),
                stockDto.getName(),
                stockDto.getPrice(),
                stockDto.getReleasedDate(),
                stockDto.getManufacturer(),
                stockDto.getWeight()
        );

        try{
            stockRepository.save(newStock);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
