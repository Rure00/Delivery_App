package com.delivery.app.Delivery.dao;

import com.delivery.app.Delivery.data.dto.request.MarketIdDto;
import com.delivery.app.Delivery.data.dto.request.ReviewIdDto;
import com.delivery.app.Delivery.data.dto.request.UserIdDto;
import com.delivery.app.Delivery.data.dto.request.review.SaveReviewDto;
import com.delivery.app.Delivery.data.dto.response.review.GetMarketReviewsResponseDto;
import com.delivery.app.Delivery.data.dto.response.review.GetMyReviewsResponseDto;

public interface ReviewDAO {
    Boolean saveReview(SaveReviewDto saveReviewDto);

    GetMyReviewsResponseDto getMyReviews(UserIdDto userIdDto);

    GetMarketReviewsResponseDto getMarketReviews(MarketIdDto marketIdDto);

    Boolean removeReview(ReviewIdDto reviewIdDto);
}
