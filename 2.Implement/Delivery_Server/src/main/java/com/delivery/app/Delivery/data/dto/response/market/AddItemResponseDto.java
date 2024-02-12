package com.delivery.app.Delivery.data.dto.response.market;

import com.delivery.app.Delivery.data.dto.response.BaseResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AddItemResponseDto implements BaseResponseDto {
    String description;

}
