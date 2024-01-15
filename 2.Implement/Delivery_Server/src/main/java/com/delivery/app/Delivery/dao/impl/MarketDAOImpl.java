package com.delivery.app.Delivery.dao.impl;

import com.delivery.app.Delivery.dao.MarketDAO;
import com.delivery.app.Delivery.data.dto.request.market.MarketSignUpDto;
import com.delivery.app.Delivery.data.dto.response.market.MarketItemsResponseDto;
import com.delivery.app.Delivery.data.dto.response.market.MarketResponseDto;
import com.delivery.app.Delivery.data.dto.response.market.NearMarketsResponseDto;
import com.delivery.app.Delivery.data.entity.Market;
import com.delivery.app.Delivery.data.entity.Stock;
import com.delivery.app.Delivery.data.entity.User;
import com.delivery.app.Delivery.data.my_enum.SignUpCode;
import com.delivery.app.Delivery.repository.marekt.MarketRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class MarketDAOImpl implements MarketDAO {

    private final MarketRepository marketRepository;

    public MarketDAOImpl(MarketRepository marketRepository) { this.marketRepository = marketRepository; }

    @Override
    public MarketResponseDto getMarketDetail(Long marketId, String marketName) {

        Market market = marketRepository.getMarketDetail(marketId, marketName);

        if(market != null) {
            return market.toMarketResponseDto();
        } else
            return null;

    }

    @Override
    public NearMarketsResponseDto getNearMarketsList(Double latitude, Double longitude, Integer radius) {

        ArrayList<Market> marketsList = marketRepository.getNearMarkets(latitude, longitude, radius);

        if(!marketsList.isEmpty()) {

            NearMarketsResponseDto result = new NearMarketsResponseDto();

            for(Market element: marketsList) {
                result.addElement(element.toMarketResponseDto());
            }

            return result;
        } else
            return null;
    }

    @Override
    public MarketItemsResponseDto getMarketItemsList(Long marketId, String marketName) {

        ArrayList<Stock> stockList = marketRepository.getMarketStocks(marketId, marketName);

        if(!stockList.isEmpty()) {

            MarketItemsResponseDto result = new MarketItemsResponseDto();

            for(Stock element: stockList) {
                result.addElement(element);
            }

            return result;
        } else
            return null;


    }

    @Override
    public SignUpCode signUp(MarketSignUpDto marketSignUpDto) {

        String id = marketSignUpDto.getLoginId();
        Optional<Market> isDuplicated = marketRepository.findByLoginId(id);

        if(isDuplicated.isEmpty()) {
            Market newMarket = new Market(
                    marketSignUpDto.getName(),
                    marketSignUpDto.getLoginId(),
                    marketSignUpDto.getLoginPwd(),
                    marketSignUpDto.getPhoneNumber(),
                    marketSignUpDto.getAddress(),
                    marketSignUpDto.getLatitude(),
                    marketSignUpDto.getLongitude(),
                    marketSignUpDto.getDescription()
            );

            marketRepository.save(newMarket);

            return SignUpCode.SUCCESS;
        } else
            return SignUpCode.DUPLICATED_ID;
    }
}
