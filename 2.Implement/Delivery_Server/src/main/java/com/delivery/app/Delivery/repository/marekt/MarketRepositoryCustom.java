package com.delivery.app.Delivery.repository.marekt;

import com.delivery.app.Delivery.data.entity.Market;
import com.delivery.app.Delivery.data.entity.Stock;

import java.util.ArrayList;

public interface MarketRepositoryCustom {
    Market getMarketDetail(Long marketId, String marketName);

    ArrayList<Market>  getNearMarkets(Double latitude, Double longitude, Integer radius);

    ArrayList<Stock> getMarketStocks(Long marketId, String marketName);
}
