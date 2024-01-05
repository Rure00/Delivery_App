package com.delivery.app.Delivery.service;

import com.delivery.app.Delivery.data.dto.request.market.MarketInfoDto;
import com.delivery.app.Delivery.data.dto.request.market.NearMarketsDto;
import com.delivery.app.Delivery.data.dto.response.market.MarketItemsResponseDto;
import com.delivery.app.Delivery.data.dto.response.market.MarketResponseDto;
import com.delivery.app.Delivery.data.dto.response.market.NearMarketsResponseDto;

public interface MarketService {
    MarketResponseDto getMarketInfo(MarketInfoDto marketInfoDto);
    NearMarketsResponseDto getNearMarkets(NearMarketsDto nearMarketsDto);
    MarketItemsResponseDto getMarketItems(MarketInfoDto marketInfoDto);
}
