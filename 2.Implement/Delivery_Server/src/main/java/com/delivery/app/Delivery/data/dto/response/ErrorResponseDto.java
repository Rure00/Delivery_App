package com.delivery.app.Delivery.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponseDto implements BaseResponseDto {
    private String comment;
}
