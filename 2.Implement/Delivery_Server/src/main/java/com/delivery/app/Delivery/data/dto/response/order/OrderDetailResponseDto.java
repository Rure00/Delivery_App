package com.delivery.app.Delivery.data.dto.response.order;

import com.delivery.app.Delivery.data.dto.response.BaseResponseDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
public class OrderDetailResponseDto implements BaseResponseDto {
    private ArrayList<Long> stockIdList;
    private ArrayList<Integer> countList;
    private ArrayList<Integer> costList;
    private Integer totalCost;

    private String state;
    private LocalDateTime orderDate;

    public OrderDetailResponseDto(String state, LocalDateTime orderDate) {
        this.state = state;
        this.orderDate = orderDate;

        stockIdList = new ArrayList<>();
        countList = new ArrayList<>();
        costList = new ArrayList<>();
        totalCost = 0;
    }

    public Boolean isEmpty() {
        return stockIdList.isEmpty();
    }

    public void addElement(Long id, Integer count, Integer cost) {
        if(stockIdList.contains(id)) {
            Exception e = new Exception("Id is Duplicated...");
            e.printStackTrace();
        }

        stockIdList.add(id);
        countList.add(count);
        costList.add(cost);

        totalCost += cost * count;
    }
}
