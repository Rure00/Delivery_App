package com.delivery.app.Delivery.service.impl;

import com.delivery.app.Delivery.dao.ReviewDAO;
import com.delivery.app.Delivery.data.dto.request.MarketIdDto;
import com.delivery.app.Delivery.data.dto.request.ReviewIdDto;
import com.delivery.app.Delivery.data.dto.request.UserIdDto;
import com.delivery.app.Delivery.data.dto.request.review.SaveReviewDto;
import com.delivery.app.Delivery.data.dto.response.review.GetMarketReviewsResponseDto;
import com.delivery.app.Delivery.data.dto.response.review.GetMyReviewsResponseDto;
import com.delivery.app.Delivery.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDAO reviewDAO;

    @Autowired
    public ReviewServiceImpl(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }


    @Override
    public Boolean saveReview(SaveReviewDto saveReviewDto) {
        return reviewDAO.saveReview(saveReviewDto);
    }

    @Override
    public GetMyReviewsResponseDto getMyReviews(UserIdDto userIdDto) {
        return reviewDAO.getMyReviews(userIdDto);
    }

    @Override
    public GetMarketReviewsResponseDto getMarketReviews(MarketIdDto marketIdDto) {
        return reviewDAO.getMarketReviews(marketIdDto);
    }

    @Override
    public Boolean removeReview(ReviewIdDto reviewIdDto) {
        return reviewDAO.removeReview(reviewIdDto);
    }
}
