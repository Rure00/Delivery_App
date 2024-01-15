package com.delivery.app.Delivery.dao;

import com.delivery.app.Delivery.data.dto.request.StockIdDto;
import com.delivery.app.Delivery.data.dto.request.stock.StockDto;
import com.delivery.app.Delivery.data.dto.response.review.GetMarketReviewsResponseDto;
import com.delivery.app.Delivery.data.dto.response.stock.StockDetailResponseDto;
import com.delivery.app.Delivery.data.entity.Stock;

public interface StockDAO {
    StockDetailResponseDto getDetail(StockIdDto stockIdDto);
    Stock getStock(Long stockId);

    boolean add(StockDto stockDto);
}
