package com.project.deliveryapp.retrofit.dto.request.stock

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class SaveStockDto(
    val stockId: Double,
    val name: String,
    val price: Int,
    val releasedDate: LocalDate,
    val manufacturer: String,
    val weight: Int
): Parcelable
