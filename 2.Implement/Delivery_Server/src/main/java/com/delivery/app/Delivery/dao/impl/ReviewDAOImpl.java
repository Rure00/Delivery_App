package com.delivery.app.Delivery.dao.impl;

import com.delivery.app.Delivery.dao.ReviewDAO;
import com.delivery.app.Delivery.data.dto.request.MarketIdDto;
import com.delivery.app.Delivery.data.dto.request.ReviewIdDto;
import com.delivery.app.Delivery.data.dto.request.UserIdDto;
import com.delivery.app.Delivery.data.dto.request.review.SaveReviewDto;
import com.delivery.app.Delivery.data.dto.response.review.GetMarketReviewsResponseDto;
import com.delivery.app.Delivery.data.dto.response.review.GetMyReviewsResponseDto;
import com.delivery.app.Delivery.data.entity.Review;
import com.delivery.app.Delivery.data.entity.User;
import com.delivery.app.Delivery.repository.review.ReviewRepository;
import com.delivery.app.Delivery.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ReviewDAOImpl implements ReviewDAO {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReviewDAOImpl(ReviewRepository reviewRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Long saveReview(SaveReviewDto saveReviewDto) {

        Long userId = saveReviewDto.getUserId();
        Long marketId = saveReviewDto.getMarketId();
        String marketName = saveReviewDto.getMarketName();
        String comment = saveReviewDto.getComment();
        Float score = saveReviewDto.getScore();

        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) {
            System.err.println("Wrong Id Param.");
            return null;
        }

        Review newReview = new Review(
                userId, marketId, marketName, userOptional.get().getNickname(),comment, score
        );


        try {
            Review review = reviewRepository.save(newReview);
            return review.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public GetMyReviewsResponseDto getMyReviews(UserIdDto userIdDto) {
        GetMyReviewsResponseDto result = new GetMyReviewsResponseDto();
        Long userId = userIdDto.getUserId();
        try {
            List<Review> myReviews = reviewRepository.getReviewByUserId(userId);
            for(Review review: myReviews) {
                result.addElement(
                        review.getId(),
                        review.getMarketId(),
                        review.getMarketName(),
                        review.getComment(),
                        review.getScore(),
                        review.getCreatedAt()
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public GetMarketReviewsResponseDto getMarketReviews(MarketIdDto marketIdDto) {

        GetMarketReviewsResponseDto result = new GetMarketReviewsResponseDto();
        Long marketId = marketIdDto.getMarketId();
        try {
            List<Review> myReviews = reviewRepository.getReviewByMarketId(marketId);
            for(Review review: myReviews) {
                result.addElement(
                        review.getId(),
                        review.getComment(),
                        review.getUserNickname(),
                        review.getScore(),
                        review.getCreatedAt()
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Boolean removeReview(ReviewIdDto reviewIdDto) {
        Long reviewId = reviewIdDto.getReviewId();
        try {
            reviewRepository.deleteById(reviewId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
