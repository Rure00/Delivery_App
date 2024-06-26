package com.project.deliveryapp.retrofit.dto.response.review

import com.project.deliveryapp.retrofit.dto.ResponseDto
import com.project.deliveryapp.toList
import org.json.JSONObject
import java.time.LocalDateTime

class GetMyReviewsResponseDto(json: JSONObject): ResponseDto {
    val idList: List<Long>
    val marketIdList: List<Long>
    val marketNameList: List<String>
    val commentList: List<String>
    val scoreList: List<Float>
    val dateList: List<LocalDateTime>

    init {
        val obj = json.getJSONObject("responseDto")

        idList = obj.getJSONArray("id").toList()
        marketIdList = obj.getJSONArray("marketId").toList()
        marketNameList = obj.getJSONArray("marketName").toList()
        commentList = obj.getJSONArray("comment").toList()
        scoreList = obj.getJSONArray("score").toList()
        dateList = obj.getJSONArray("date").toList()
    }
}