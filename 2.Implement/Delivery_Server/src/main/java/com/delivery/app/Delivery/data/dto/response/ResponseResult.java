package com.delivery.app.Delivery.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult {
    boolean flag;
    BaseResponseDto responseDto;
}
