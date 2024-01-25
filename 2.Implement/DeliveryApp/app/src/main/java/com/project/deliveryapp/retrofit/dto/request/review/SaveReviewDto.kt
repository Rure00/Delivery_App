package com.project.deliveryapp.retrofit.dto.request.review

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SaveReviewDto(
    val userId: Long,
    val marketId: Long,
    val marketName: String,
    val comment: String,
    val score: Float,

): Parcelable
