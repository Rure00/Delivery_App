package com.delivery.app.Delivery.data.dto.response.market;

import com.delivery.app.Delivery.data.dto.response.BaseResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MarketSignUpResponseDto implements BaseResponseDto {
    private String description;
}
