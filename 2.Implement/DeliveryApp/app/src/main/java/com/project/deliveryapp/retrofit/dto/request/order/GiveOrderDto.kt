package com.project.deliveryapp.retrofit.dto.request.order

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GiveOrderDto(
    val userId: Long,
    val cartId: Long
): Parcelable
