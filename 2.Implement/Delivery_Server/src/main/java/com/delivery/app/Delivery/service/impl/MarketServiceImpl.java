package com.delivery.app.Delivery.service.impl;

import com.delivery.app.Delivery.dao.MarketDAO;
import com.delivery.app.Delivery.data.dto.request.market.MarketInfoDto;
import com.delivery.app.Delivery.data.dto.request.market.MarketSignUpDto;
import com.delivery.app.Delivery.data.dto.request.market.NearMarketsDto;
import com.delivery.app.Delivery.data.dto.response.market.MarketItemsResponseDto;
import com.delivery.app.Delivery.data.dto.response.market.MarketResponseDto;
import com.delivery.app.Delivery.data.dto.response.market.NearMarketsResponseDto;
import com.delivery.app.Delivery.data.my_enum.SignUpCode;
import com.delivery.app.Delivery.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketServiceImpl implements MarketService {

    private final MarketDAO marketDao;

    @Autowired
    public MarketServiceImpl(MarketDAO marketDao) {this.marketDao = marketDao;}


    @Override
    public MarketResponseDto getMarketInfo(MarketInfoDto marketInfoDto) {

        Long marketId = marketInfoDto.getId();
        String marketName = marketInfoDto.getName();

        return marketDao.getMarketDetail(marketId, marketName);
    }

    @Override
    public NearMarketsResponseDto getNearMarkets(NearMarketsDto nearMarketsDto) {

        Double latitude = nearMarketsDto.getLatitude();
        Double longitude = nearMarketsDto.getLongitude();
        Integer radius = nearMarketsDto.getRadius();

        return marketDao.getNearMarketsList(latitude, longitude, radius);
    }

    @Override
    public MarketItemsResponseDto getMarketItems(MarketInfoDto marketInfoDto) {

        Long marketId = marketInfoDto.getId();
        String marketName = marketInfoDto.getName();

        return marketDao.getMarketItemsList(marketId, marketName);
    }

    @Override
    public SignUpCode trySignUp(MarketSignUpDto marketSignUpDto) {
        return marketDao.signUp(marketSignUpDto);
    }
}
