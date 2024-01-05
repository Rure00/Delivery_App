package com.delivery.app.Delivery.dao;

import com.delivery.app.Delivery.data.dto.response.market.MarketItemsResponseDto;
import com.delivery.app.Delivery.data.dto.response.market.MarketResponseDto;
import com.delivery.app.Delivery.data.dto.response.market.NearMarketsResponseDto;

public interface MarketDAO {

    MarketResponseDto getMarketDetail(Long marketId, String marketName);

    NearMarketsResponseDto getNearMarketsList(Double latitude, Double longitude, Integer radius);

    MarketItemsResponseDto getMarketItemsList(Long marketId, String marketName);
}
