package com.delivery.app.Delivery.service;

import com.delivery.app.Delivery.data.dto.request.StockIdDto;
import com.delivery.app.Delivery.data.dto.request.stock.StockDto;
import com.delivery.app.Delivery.data.dto.response.review.GetMarketReviewsResponseDto;
import com.delivery.app.Delivery.data.dto.response.stock.StockDetailResponseDto;

public interface StockService {
    StockDetailResponseDto getStockDetail(StockIdDto stockIdDto);

    boolean addNewStock(StockDto stockDto);
}
