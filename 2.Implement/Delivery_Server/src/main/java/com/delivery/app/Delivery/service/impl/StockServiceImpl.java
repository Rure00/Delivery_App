package com.delivery.app.Delivery.service.impl;

import com.delivery.app.Delivery.dao.StockDAO;
import com.delivery.app.Delivery.data.dto.request.StockIdDto;
import com.delivery.app.Delivery.data.dto.response.review.GetMarketReviewsResponseDto;
import com.delivery.app.Delivery.data.dto.response.stock.StockDetailResponseDto;
import com.delivery.app.Delivery.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

    private final StockDAO stockDAO;

    @Autowired
    public StockServiceImpl(StockDAO stockDAO) {
        this.stockDAO = stockDAO;
    }


    @Override
    public StockDetailResponseDto getStockDetail(StockIdDto stockIdDto) {
        return stockDAO.getDetail(stockIdDto);
    }
}
