package com.delivery.app.Delivery.dao.impl;

import com.delivery.app.Delivery.dao.MarketDAO;
import com.delivery.app.Delivery.data.dto.request.market.AddItemDto;
import com.delivery.app.Delivery.data.dto.request.market.MarketSignUpDto;
import com.delivery.app.Delivery.data.dto.response.market.AddItemResponseDto;
import com.delivery.app.Delivery.data.dto.response.market.MarketItemsResponseDto;
import com.delivery.app.Delivery.data.dto.response.market.MarketResponseDto;
import com.delivery.app.Delivery.data.dto.response.market.NearMarketsResponseDto;
import com.delivery.app.Delivery.data.entity.Market;
import com.delivery.app.Delivery.data.entity.Stock;
import com.delivery.app.Delivery.data.my_enum.SignUpCode;
import com.delivery.app.Delivery.repository.marekt.MarketRepository;
import com.delivery.app.Delivery.repository.stock.StockRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class MarketDAOImpl implements MarketDAO {

    private final MarketRepository marketRepository;
    private final StockRepository stockRepository;

    public MarketDAOImpl(MarketRepository marketRepository, StockRepository stockRepository) {
        this.marketRepository = marketRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public MarketResponseDto getMarketDetail(Long marketId) {

        Optional<Market> marketOptional = marketRepository.findById(marketId);

        return marketOptional.map(Market::toMarketResponseDto).orElse(null);

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
    public MarketItemsResponseDto getMarketItemsList(Long marketId) {

        ArrayList<Stock> stockList = marketRepository.getMarketStocks(marketId);

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

    @Override
    public AddItemResponseDto addItem(AddItemDto addItemDto) {
        AddItemResponseDto responseDto = new AddItemResponseDto();
        Optional<Stock> stockOptional = stockRepository.findById(addItemDto.getStockId());
        Optional<Market> marketOptional = marketRepository.findById(addItemDto.getMarketId());

        if(stockOptional.isPresent() && marketOptional.isPresent()) {
            Market market = marketOptional.get();
            market.addNewItem(stockOptional.get());

            marketRepository.save(market);

            responseDto.setDescription("성공");
        } else {
            responseDto.setDescription("실패");
        }

        return responseDto;
    }
}
