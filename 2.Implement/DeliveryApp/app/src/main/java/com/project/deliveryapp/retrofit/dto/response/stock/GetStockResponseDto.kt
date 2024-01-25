package com.project.deliveryapp.retrofit.dto.response.stock

import com.project.deliveryapp.retrofit.dto.ResponseDto
import com.project.deliveryapp.toLocalDateTime
import org.json.JSONObject
import java.time.LocalDateTime

class GetStockResponseDto(json: JSONObject): ResponseDto {
    val id: Long
    val name: String
    val price: Int
    val releaseDate: LocalDateTime
    val manufacturer: String
    val weight: Int

    init {
        val obj = json.getJSONObject("responseDto")

        id = obj.getLong("id")
        name = obj.getString("name")
        price = obj.getInt("price")
        releaseDate = obj.getString("releaseDate").toLocalDateTime()
        manufacturer = obj.getString("manufacturer")
        weight = obj.getInt("weight")
    }
}