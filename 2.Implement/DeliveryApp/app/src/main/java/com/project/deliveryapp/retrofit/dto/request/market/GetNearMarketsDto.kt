package com.project.deliveryapp.retrofit.dto.request.market

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetNearMarketsDto(
    val latitude: Double,
    val longitude: Double,
    val radius: Int
): Parcelable
