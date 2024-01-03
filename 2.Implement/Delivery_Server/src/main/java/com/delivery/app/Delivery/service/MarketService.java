package com.delivery.app.Delivery.service;

import com.delivery.app.Delivery.data.dto.market.MarketInfoDto;
import com.delivery.app.Delivery.data.dto.market.NearMarketsDto;
import com.delivery.app.Delivery.data.dto.response.MarketItemsResponseDto;
import com.delivery.app.Delivery.data.dto.response.MarketResponseDto;
import com.delivery.app.Delivery.data.dto.response.NearMarketsResponseDto;

public interface MarketService {
    MarketResponseDto getMarketInfo(MarketInfoDto marketInfoDto);
    NearMarketsResponseDto getNearMarkets(NearMarketsDto nearMarketsDto);
    MarketItemsResponseDto getMarketItems(MarketInfoDto marketInfoDto);
}
