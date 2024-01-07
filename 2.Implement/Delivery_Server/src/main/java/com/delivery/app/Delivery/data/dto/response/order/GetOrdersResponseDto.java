package com.delivery.app.Delivery.data.dto.response.order;

import com.delivery.app.Delivery.data.dto.response.BaseResponseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GetOrdersResponseDto implements BaseResponseDto {

    private ArrayList<Long> idList;
    private ArrayList<String> marketNameList;
    private ArrayList<Integer> costList;
    private ArrayList<LocalDateTime> dateList;

    public GetOrdersResponseDto() {
        idList = new ArrayList<>();
        marketNameList = new ArrayList<>();
        costList = new ArrayList<>();
        dateList = new ArrayList<>();
    }

    public boolean isEmpty() {
        return idList.isEmpty();
    }
    public void addElement(Long id, String marketName, Integer cost, LocalDateTime date) {
        idList.add(id);
        marketNameList.add(marketName);
        costList.add(cost);
        dateList.add(date);
    }
}
