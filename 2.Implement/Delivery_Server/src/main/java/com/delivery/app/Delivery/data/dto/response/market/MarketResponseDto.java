package com.delivery.app.Delivery.data.dto.response.market;


import com.delivery.app.Delivery.data.dto.response.BaseResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MarketResponseDto implements BaseResponseDto {
    private Long id;
    private String name;
    private Float score;
    private String phoneNumber;
    private String address;
    private Double latitude;
    private Double longitude;
    private String description;
}
