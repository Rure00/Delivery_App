package com.delivery.app.Delivery.dao;

import com.delivery.app.Delivery.data.dto.request.StockIdDto;
import com.delivery.app.Delivery.data.dto.response.review.GetMarketReviewsResponseDto;
import com.delivery.app.Delivery.data.dto.response.stock.StockDetailResponseDto;

public interface StockDAO {
    StockDetailResponseDto getDetail(StockIdDto stockIdDto);
}
