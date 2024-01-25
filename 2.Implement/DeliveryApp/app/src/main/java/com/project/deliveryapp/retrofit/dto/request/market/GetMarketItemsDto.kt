package com.project.deliveryapp.retrofit.dto.request.market

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetMarketItemsDto (
    val name: String,
    val id: Double
): Parcelable