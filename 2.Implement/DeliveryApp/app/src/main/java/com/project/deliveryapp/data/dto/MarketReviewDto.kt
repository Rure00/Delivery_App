package com.project.deliveryapp.data.dto

import java.time.LocalDateTime

data class MarketReviewDto(
    val id: Long,
    val comment: String,
    val score: Float,
    val date: LocalDateTime
)
