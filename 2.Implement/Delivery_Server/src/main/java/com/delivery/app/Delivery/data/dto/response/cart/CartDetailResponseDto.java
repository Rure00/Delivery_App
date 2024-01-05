package com.delivery.app.Delivery.data.dto.response.cart;

import com.delivery.app.Delivery.data.dto.response.BaseResponseDto;
import com.delivery.app.Delivery.data.entity.Stock;

import java.util.ArrayList;

public class CartDetailResponseDto implements BaseResponseDto {
    Long id;
    ArrayList<Stock> stockList;
    ArrayList<Integer> stockCount;

    public CartDetailResponseDto() {
        stockList = new ArrayList<>();
        stockCount = new ArrayList<>();
    }
    public void addElement(Stock stock, Integer num) {
        stockList.add(stock);
        stockCount.add(num);
    }
    public boolean isEmpty() {
        return stockList.isEmpty();
    }
}
