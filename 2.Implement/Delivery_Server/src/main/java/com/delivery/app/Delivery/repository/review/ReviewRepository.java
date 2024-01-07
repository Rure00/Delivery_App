package com.delivery.app.Delivery.repository.review;

import com.delivery.app.Delivery.data.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> getReviewByUserId(Long userId);
    List<Review> getReviewByMarketId(Long marketId);
}
