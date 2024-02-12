package com.delivery.app.Delivery.dao;

import com.delivery.app.Delivery.data.dto.request.market.AddItemDto;
import com.delivery.app.Delivery.data.dto.request.market.MarketSignUpDto;
import com.delivery.app.Delivery.data.dto.response.market.AddItemResponseDto;
import com.delivery.app.Delivery.data.dto.response.market.MarketItemsResponseDto;
import com.delivery.app.Delivery.data.dto.response.market.MarketResponseDto;
import com.delivery.app.Delivery.data.dto.response.market.NearMarketsResponseDto;
import com.delivery.app.Delivery.data.my_enum.SignUpCode;

public interface MarketDAO {

    MarketResponseDto getMarketDetail(Long marketId);

    NearMarketsResponseDto getNearMarketsList(Double latitude, Double longitude, Integer radius);

    MarketItemsResponseDto getMarketItemsList(Long marketId);

    SignUpCode signUp(MarketSignUpDto marketSignUpDto);

    AddItemResponseDto addItem(AddItemDto addItemDto);
}
