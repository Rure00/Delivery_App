package com.delivery.app.Delivery.data.dto.response.cart;

import com.delivery.app.Delivery.data.dto.response.BaseResponseDto;
import com.delivery.app.Delivery.data.entity.Stock;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class GetCartsResponseDto implements BaseResponseDto {
    ArrayList<Long> idList;
    ArrayList<String> marketNameList;
    ArrayList<Integer> costList;

    public GetCartsResponseDto() {
        idList = new ArrayList<>();
        marketNameList = new ArrayList<>();
        costList = new ArrayList<>();
    }
    public void addElement(Long id,String name, Integer cost) {
        idList.add(id);
        marketNameList.add(name);
        costList.add(cost);
    }
    public boolean isEmpty() {
        return idList.isEmpty();
    }
}
