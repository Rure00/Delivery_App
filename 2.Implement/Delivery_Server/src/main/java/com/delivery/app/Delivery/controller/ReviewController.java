package com.delivery.app.Delivery.controller;

import com.delivery.app.Delivery.data.dto.request.MarketIdDto;
import com.delivery.app.Delivery.data.dto.request.ReviewIdDto;
import com.delivery.app.Delivery.data.dto.request.UserIdDto;
import com.delivery.app.Delivery.data.dto.request.review.SaveReviewDto;
import com.delivery.app.Delivery.data.dto.response.ResponseResult;
import com.delivery.app.Delivery.data.dto.response.review.GetMarketReviewsResponseDto;
import com.delivery.app.Delivery.data.dto.response.review.GetMyReviewsResponseDto;
import com.delivery.app.Delivery.data.dto.response.review.SaveReviewResponseDto;
import com.delivery.app.Delivery.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private ReviewService service;

    @Autowired
    public ReviewController(ReviewService service) { this.service = service; }

    @PostMapping("/save")
    public ResponseEntity<ResponseResult> saveReview(@RequestBody SaveReviewDto saveReviewDto) {

        ResponseResult result = new ResponseResult();

        try {
            Long id = service.saveReview(saveReviewDto);
            result.setFlag(true);
            result.setResponseDto(new SaveReviewResponseDto(id));
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            result.setFlag(false);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(result);
        }
    }

    @PostMapping("/mine")
    public ResponseEntity<ResponseResult> getMyReviews(@RequestBody UserIdDto userIdDto ) {
        GetMyReviewsResponseDto responseDto = service.getMyReviews(userIdDto);
        ResponseResult result = new ResponseResult();

        if(responseDto.isEmpty()) {
            result.setFlag(true);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            result.setFlag(false);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(result);
        }
    }

    @PostMapping("/get")
    public ResponseEntity<ResponseResult> getMarketReviews(@RequestBody MarketIdDto marketIdDto) {
        GetMarketReviewsResponseDto reviews = service.getMarketReviews(marketIdDto);
        ResponseResult result = new ResponseResult();

        if(!reviews.isEmpty()) {
            result.setResponseDto(reviews);
            result.setFlag(true);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            result.setFlag(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

    }

    @PostMapping("/remove")
    public ResponseEntity<ResponseResult> removeReview(@RequestBody ReviewIdDto reviewIdDto) {
        Boolean isSuccess = service.removeReview(reviewIdDto);
        ResponseResult result = new ResponseResult();

        if(isSuccess) {
            result.setFlag(true);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            result.setFlag(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }
}
