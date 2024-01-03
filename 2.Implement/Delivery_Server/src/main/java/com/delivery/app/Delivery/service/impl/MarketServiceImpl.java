package com.delivery.app.Delivery.service.impl;

import com.delivery.app.Delivery.dao.MarketDAO;
import com.delivery.app.Delivery.data.dto.market.MarketInfoDto;
import com.delivery.app.Delivery.data.dto.market.NearMarketsDto;
import com.delivery.app.Delivery.data.dto.response.MarketItemsResponseDto;
import com.delivery.app.Delivery.data.dto.response.MarketResponseDto;
import com.delivery.app.Delivery.data.dto.response.NearMarketsResponseDto;
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
}
