package com.delivery.app.Delivery.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpResponseDto {
    private boolean isSuccess;
    private String des;
}
