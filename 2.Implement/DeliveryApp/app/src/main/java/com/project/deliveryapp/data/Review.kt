package com.project.deliveryapp.data

import android.media.Image
import java.time.LocalDateTime


data class Review(
    val id: Long = 0,
    val userId: Long,
    val marketId: Long,

    val userNickname: String,
    val marketName: String,
    val comment: String,
    val score: Float,
    val date: LocalDateTime,
)
