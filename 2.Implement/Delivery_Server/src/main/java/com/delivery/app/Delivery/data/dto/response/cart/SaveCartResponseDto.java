package com.delivery.app.Delivery.data.dto.response.cart;

import com.delivery.app.Delivery.data.dto.response.BaseResponseDto;

public class SaveCartResponseDto implements BaseResponseDto{
    private Long id;

    public SaveCartResponseDto(Long id) {
        this.id = id;
    }
}
