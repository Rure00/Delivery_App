package com.project.deliveryapp.retrofit.dto

import android.location.Location
import com.naver.maps.geometry.LatLng
import com.project.deliveryapp.data.MarketData


data class MarketDataDto(
    private val id: Int,
    val name: String,
    //val logInId: String,
    //val loginPwd: String,
    val score: Float,
    val phoneNumber: String,
    val address: String,
    val description: String,
    val latitude: Double,
    val longitude: Double
) {
    fun toMarketData() =
        MarketData(
            id = id,
            name = name,
            address = address,
            phoneNumber = phoneNumber,
            description = description,
            location = LatLng(latitude, longitude),
            score = score
        )
}
