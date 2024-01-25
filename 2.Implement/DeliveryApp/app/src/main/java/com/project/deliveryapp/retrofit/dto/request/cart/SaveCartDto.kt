package com.project.deliveryapp.retrofit.dto.request.cart

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SaveCartDto(
    val userId: Long,
    val marketId: Long,
    val marketName: String,
    val itemIdList: List<Long>,
    val itemNumList: List<Int>,
    val cost: Int
): Parcelable
