package com.project.deliveryapp.retrofit.dto.response.market

import com.project.deliveryapp.retrofit.dto.ResponseDto
import org.json.JSONObject

class MarketSignUpResponseDto(json: JSONObject): ResponseDto {
    val description: String

    init {
        val obj = json.getJSONObject("responseDto")

        description = obj.getString("description")
    }
}