package com.project.deliveryapp.data

import android.media.Image


data class Review(
    private val id: Int = 0,
    private val userId: Int,
    private val marketId: Int,

    val userNickname: String,
    val comment: String,
    val score: Float,

    val pictures: ArrayList<Image>?
)
