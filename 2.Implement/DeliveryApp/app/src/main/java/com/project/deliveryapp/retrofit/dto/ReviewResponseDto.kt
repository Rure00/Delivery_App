package com.project.deliveryapp.retrofit.dto

import com.project.deliveryapp.data.Review

data class ReviewResponseDto(
    val hasMyReview: Boolean,
    val reviews: List<Review>?
)
