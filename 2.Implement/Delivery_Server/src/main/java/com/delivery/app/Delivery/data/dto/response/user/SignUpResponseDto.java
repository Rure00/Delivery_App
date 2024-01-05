package com.delivery.app.Delivery.data.dto.response.user;

import com.delivery.app.Delivery.data.dto.response.BaseResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpResponseDto implements BaseResponseDto {
    private String description;
}
