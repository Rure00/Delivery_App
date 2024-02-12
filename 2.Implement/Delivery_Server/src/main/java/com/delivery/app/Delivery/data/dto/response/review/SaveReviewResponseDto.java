package com.delivery.app.Delivery.data.dto.response.review;

import com.delivery.app.Delivery.data.dto.response.BaseResponseDto;
import lombok.Getter;

@Getter
public class SaveReviewResponseDto implements BaseResponseDto {
    private Long id;

    public SaveReviewResponseDto(Long id) {
        this.id = id;
    }
}
