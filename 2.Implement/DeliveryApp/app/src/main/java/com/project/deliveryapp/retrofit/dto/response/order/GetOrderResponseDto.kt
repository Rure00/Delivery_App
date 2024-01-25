package com.project.deliveryapp.retrofit.dto.response.order

import com.project.deliveryapp.retrofit.dto.ResponseDto
import com.project.deliveryapp.toList
import org.json.JSONObject
import java.time.LocalDateTime

class GetOrderResponseDto(json: JSONObject): ResponseDto {
    val idList: List<Long>
    val marketNameList: List<String>
    val costList: List<Int>
    val stateList: List<String>
    val dateList: List<LocalDateTime>

    init {
        val obj = json.getJSONObject("responseDto")

        idList = obj.getJSONArray("idList").toList()
        marketNameList = obj.getJSONArray("marketNameList").toList()
        costList = obj.getJSONArray("costList").toList()
        stateList = obj.getJSONArray("stateList").toList()
        dateList = obj.getJSONArray("dateList").toList()
    }
}