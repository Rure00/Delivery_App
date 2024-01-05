package com.delivery.app.Delivery.data.dto.response.market;

import com.delivery.app.Delivery.data.dto.response.BaseResponseDto;
import com.delivery.app.Delivery.data.entity.Stock;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class MarketItemsResponseDto implements BaseResponseDto {
    ArrayList<Stock> stockList;

    public MarketItemsResponseDto() {
        stockList = new ArrayList<>();
    }

    public void addElement(Stock data) {
        stockList.add(data);
    }
}
