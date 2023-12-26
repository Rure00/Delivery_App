package com.project.deliveryapp.data

import android.media.Image
import java.time.LocalDateTime


data class Review(
    private val id: Int = 0,
    private val userId: Int,
    private val marketId: Int,

    val userNickname: String,
    val marketName: String,
    val comment: String,
    val score: Float,
    val date: LocalDateTime,
)
