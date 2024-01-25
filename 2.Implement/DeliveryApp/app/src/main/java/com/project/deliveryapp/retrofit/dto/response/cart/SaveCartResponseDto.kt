package com.project.deliveryapp.retrofit.dto.response.cart

import com.project.deliveryapp.retrofit.dto.ResponseDto
import org.json.JSONObject

class SaveCartResponseDto(json: JSONObject): ResponseDto {
    val id: Long

    init {
        val obj = json.getJSONObject("responseDto")

        id = obj.getLong("id")
    }
}