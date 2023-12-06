package com.project.deliveryapp.data

import android.location.Location
import com.naver.maps.geometry.LatLng

data class MarketData(
    val id: Int,
    val name: String,
    val score: Float,
    //val signInId: String,
    //val signInPwd: String,
    val phoneNumber: String,
    val address: String,
    val description: String,
    val location: LatLng
)
