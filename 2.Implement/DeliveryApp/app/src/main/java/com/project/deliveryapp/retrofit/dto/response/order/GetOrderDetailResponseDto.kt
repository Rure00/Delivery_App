package com.project.deliveryapp.retrofit.dto.response.order

import com.project.deliveryapp.retrofit.dto.ResponseDto
import com.project.deliveryapp.toList
import com.project.deliveryapp.toLocalDateTime
import org.json.JSONObject
import java.time.LocalDateTime

class GetOrderDetailResponseDto(json: JSONObject): ResponseDto {
    val stockIdList: List<Long>
    val countList: List<Int>
    val costList: List<Int>
    val totalCost: Int
    val date: LocalDateTime
    val state: String

    init {
        val obj = json.getJSONObject("responseDto")

        stockIdList = obj.getJSONArray("stockIdList").toList()
        countList = obj.getJSONArray("countList").toList()
        costList = obj.getJSONArray("costList").toList()

        totalCost = obj.getInt("totalCost")
        date = obj.getString("date").toLocalDateTime()
        state = obj.getString("state")
    }
}