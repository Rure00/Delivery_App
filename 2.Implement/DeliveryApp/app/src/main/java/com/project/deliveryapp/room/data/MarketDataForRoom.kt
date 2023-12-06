package com.project.deliveryapp.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.naver.maps.geometry.LatLng
import com.project.deliveryapp.data.MarketData

@Entity
data class MarketDataForRoom(
    @PrimaryKey
    val id: Int,
    val name: String,
    val score: Float,
    val phoneNumber: String,
    val description: String,
    var usingNumber: Int,
    val address: String,
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
