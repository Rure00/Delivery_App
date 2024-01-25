package com.project.deliveryapp.retrofit.dto.response.user

import com.project.deliveryapp.retrofit.dto.ResponseDto
import org.json.JSONObject

class SignUpResponseDto(json: JSONObject): ResponseDto {
    val description: String

    init {
        val obj = json.getJSONObject("responseDto")

        description = obj.getString("description")
    }
}