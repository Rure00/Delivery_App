package com.delivery.app.Delivery.data.dto.request.market;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MarketSignUpDto {
    private String name;
    private String loginId;
    private String loginPwd;
    private String phoneNumber;
    private Double longitude;
    private Double latitude;
    private String description;
    private String address;

    public boolean hasNull() {
        return   name == null
                || loginId == null
                || loginPwd == null
                || phoneNumber == null
                || longitude == null
                || latitude == null
                || description == null
                || address == null;
    }
}
