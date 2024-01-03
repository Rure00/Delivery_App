package com.delivery.app.Delivery.data.dto.response;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MarketResponseDto implements BaseResponseDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String address;
    private Double latitude;
    private Double longitude;
    private String description;
}
