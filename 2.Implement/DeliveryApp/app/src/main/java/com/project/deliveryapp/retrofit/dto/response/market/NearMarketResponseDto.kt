package com.project.deliveryapp.retrofit.dto.response.market

import com.project.deliveryapp.retrofit.dto.ResponseDto
import com.project.deliveryapp.toList
import org.json.JSONObject

class NearMarketResponseDto(json: JSONObject): ResponseDto {
    val id: List<Long>
    val name: List<String>
    val phoneNumber: List<String>
    val address: List<String>
    val latitude: List<Double>
    val longitude: List<Double>
    val description: List<String>

    init {
        val obj = json.getJSONObject("responseDto")

        id = obj.getJSONArray("id").toList()
        name = obj.getJSONArray("name").toList()
        phoneNumber = obj.getJSONArray("phoneNumber").toList()
        address = obj.getJSONArray("address").toList()
        latitude = obj.getJSONArray("latitude").toList()
        longitude = obj.getJSONArray("longitude").toList()
        description = obj.getJSONArray("description").toList()
    }
}