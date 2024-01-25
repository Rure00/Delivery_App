package com.project.deliveryapp.retrofit.dto.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarketIdDto(
    val marketId: Long
): Parcelable