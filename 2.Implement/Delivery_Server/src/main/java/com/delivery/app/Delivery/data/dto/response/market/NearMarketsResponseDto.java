package com.delivery.app.Delivery.data.dto.response.market;

import com.delivery.app.Delivery.data.dto.response.BaseResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
public class NearMarketsResponseDto implements BaseResponseDto {

    private ArrayList<Long> id;
    private ArrayList<String> name;
    private ArrayList<Float> score;
    private ArrayList<String> phoneNumber;
    private ArrayList<String> address;
    private ArrayList<Double> latitude;
    private ArrayList<Double> longitude;
    private ArrayList<String> description;

    public NearMarketsResponseDto() {
        id = new ArrayList<>();
        name = new ArrayList<>();
        score = new ArrayList<>();
        phoneNumber = new ArrayList<>();
        address = new ArrayList<>();
        latitude = new ArrayList<>();
        longitude = new ArrayList<>();
        description = new ArrayList<>();
    }


    public void addElement(MarketResponseDto data) {
        id.add(data.getId());
        name.add(data.getName());
        score.add(data.getScore());
        phoneNumber.add(data.getPhoneNumber());
        address.add(data.getAddress());
        latitude.add(data.getLatitude());
        longitude.add(data.getLongitude());
        description.add(data.getDescription());
    }



}
