package com.delivery.app.Delivery.data.dto.request.review;

import lombok.Getter;

@Getter
public class SaveReviewDto {
    Long userId;
    Long marketId;
    String comment;
    String marketName;
    Float score;
}
