package com.project.deliveryapp.retrofit.dto.response.market

import com.project.deliveryapp.retrofit.dto.ResponseDto
import org.json.JSONObject

class MarketDetailResponseDto(json: JSONObject): ResponseDto {
    val id: Long
    val name: String
    val phoneNumber: String
    val score: Float
    val address: String
    val latitude: Double
    val longitude: Double
    val description: String

    init {
        val obj = json.getJSONObject("responseDto")

        id = obj.getLong("id")
        name = obj.getString("name")
        phoneNumber = obj.getString("phoneNumber")
        score = obj.getDouble("score").toFloat()
        address = obj.getString("address")
        latitude = obj.getDouble("latitude")
        longitude = obj.getDouble("longitude")
        description = obj.getString("description")
    }
}