package com.delivery.app.Delivery.repository.marekt;

import com.delivery.app.Delivery.data.entity.*;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.ArrayList;
import java.util.List;

public class MarketRepositoryCustomImpl extends QuerydslRepositorySupport implements MarketRepositoryCustom {

    public MarketRepositoryCustomImpl(Class<?> domainClass) { super(domainClass); }

    @Override
    public Market getMarketDetail(Long marketId, String marketName) {

        QMarket market = QMarket.market;

        Market marketData = from(market)
                .where(market.id.eq(marketId).and(market.name.eq(marketName)))
                .fetchOne();

        if(marketData == null)
            System.out.println("ERROR: MARKET is NULL");

        return marketData;
    }

    @Override
    public ArrayList<Market> getNearMarkets(Double latitude, Double longitude, Integer radius) {

        QMarket market = QMarket.market;

        List<Market> marketsList = from(market)
                .where(Expressions.stringTemplate("ST_Distance_Sphere({0}, {1})",
                        Expressions.stringTemplate("POINT({0}, {1})", longitude, latitude),
                        Expressions.stringTemplate("POINT({0}, {1})", market.longitude, market.latitude))
                        .loe(radius.toString())
                ).fetchAll().fetch();

        ArrayList<Market> result = new ArrayList<>(marketsList);

        if(marketsList.isEmpty())
            System.out.println("ERROR: MARKET is NULL");

        return result;
    }

    @Override
    public ArrayList<Stock> getMarketStocks(Long marketId, String marketName) {

        QMarket market = QMarket.market;

        Market marketData = from(market)
                .where(market.id.eq(marketId).and(market.name.eq(marketName)))
                .fetchOne();

        if(marketData == null)
            System.out.println("ERROR: MARKET is NULL");

        ArrayList<Stock> result = new ArrayList<>(marketData.getStocks());



        return result;
    }
}
