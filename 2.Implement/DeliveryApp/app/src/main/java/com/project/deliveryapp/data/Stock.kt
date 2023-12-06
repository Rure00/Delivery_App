package com.project.deliveryapp.data

import android.graphics.Bitmap
import android.media.Image
import java.sql.Date
import java.time.LocalDateTime


data class Stock(
    val imageBitmap: Bitmap?,
    val id: Long,
    val name: String,
    val releaseDate: LocalDateTime,
    val manufacturer: String,
    val weight: Float,
    val price: Long
)
