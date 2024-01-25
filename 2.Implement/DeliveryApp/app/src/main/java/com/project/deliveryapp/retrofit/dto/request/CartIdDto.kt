package com.project.deliveryapp.retrofit.dto.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartIdDto (
    val cartId: Long
): Parcelable