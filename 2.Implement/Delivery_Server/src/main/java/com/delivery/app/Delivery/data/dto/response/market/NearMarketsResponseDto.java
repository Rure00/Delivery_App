package com.delivery.app.Delivery.data.dto.response.market;

import com.delivery.app.Delivery.data.dto.response.BaseResponseDto;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class NearMarketsResponseDto implements BaseResponseDto {
    ArrayList<MarketResponseDto> nearMarketsList;

    public NearMarketsResponseDto() {
        nearMarketsList = new ArrayList<>();
    }

    public void addElement(MarketResponseDto data) {
        nearMarketsList.add(data);
    }
}
