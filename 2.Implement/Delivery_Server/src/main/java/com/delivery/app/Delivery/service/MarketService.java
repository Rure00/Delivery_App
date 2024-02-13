package com.delivery.app.Delivery.service;

import com.delivery.app.Delivery.data.dto.request.market.AddItemDto;
import com.delivery.app.Delivery.data.dto.request.market.MarketInfoDto;
import com.delivery.app.Delivery.data.dto.request.market.MarketSignUpDto;
import com.delivery.app.Delivery.data.dto.request.market.NearMarketsDto;
import com.delivery.app.Delivery.data.dto.response.market.AddItemResponseDto;
import com.delivery.app.Delivery.data.dto.response.market.MarketItemsResponseDto;
import com.delivery.app.Delivery.data.dto.response.market.MarketResponseDto;
import com.delivery.app.Delivery.data.dto.response.market.NearMarketsResponseDto;
import com.delivery.app.Delivery.data.my_enum.SignUpCode;

public interface MarketService {
    MarketResponseDto getMarketInfo(MarketInfoDto marketInfoDto);
    NearMarketsResponseDto getNearMarkets(NearMarketsDto nearMarketsDto);
    MarketItemsResponseDto getMarketItems(MarketInfoDto marketInfoDto);

    SignUpCode trySignUp(MarketSignUpDto marketSignUpDto);

    AddItemResponseDto addItem(AddItemDto addItemDto);
}
