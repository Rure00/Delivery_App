package com.project.deliveryapp.retrofit.dto.response.cart

import com.project.deliveryapp.retrofit.dto.ResponseDto
import com.project.deliveryapp.toList
import org.json.JSONObject
import java.time.LocalDateTime

class GetCartsResponseDto(json: JSONObject): ResponseDto {
    val idList: List<Long>
    val marketNameList: List<String>
    val costList: List<Int>

    init {
        val obj = json.getJSONObject("responseDto")

        idList = obj.getJSONArray("idList").toList()
        marketNameList = obj.getJSONArray("marketNameList").toList()
        costList = obj.getJSONArray("costList").toList()
    }

}