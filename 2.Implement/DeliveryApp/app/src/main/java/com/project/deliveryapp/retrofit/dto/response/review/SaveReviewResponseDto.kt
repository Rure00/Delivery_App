package com.project.deliveryapp.retrofit.dto.response.review

import com.project.deliveryapp.retrofit.dto.ResponseDto
import com.project.deliveryapp.toList
import org.json.JSONObject
import java.time.LocalDateTime

class SaveReviewResponseDto(json: JSONObject): ResponseDto {
    val id: Long

    init {
        val obj = json.getJSONObject("responseDto")

        id = obj.getLong("id")
    }

}