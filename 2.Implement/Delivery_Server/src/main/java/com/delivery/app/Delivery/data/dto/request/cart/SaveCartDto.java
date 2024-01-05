package com.delivery.app.Delivery.data.dto.request.cart;

import com.delivery.app.Delivery.data.entity.Stock;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class SaveCartDto {
    Long userId;
    Long marketId;
    ArrayList<Long> itemIdList;
    ArrayList<Integer> itemNumList;
    String marketName;
    Integer cost;
}
