package com.project.deliveryapp.retrofit.dto.response.market

import com.project.deliveryapp.data.Stock
import com.project.deliveryapp.retrofit.dto.ResponseDto
import com.project.deliveryapp.toLocalDateTime
import org.json.JSONObject

class GetItemsResponseDto(json: JSONObject): ResponseDto {
    val stockList: ArrayList<Stock>

    init {
        val obj = json.getJSONObject("responseDto").getJSONArray("stockList")
        stockList = ArrayList()

        for(index in 0..obj.length()) {
            val stock = obj.getJSONObject(index)
            val newStock = Stock(
                null,
                stock.getLong("id"),
                stock.getString("name"),
                stock.getString("releasedDate").toLocalDateTime(),
                stock.getString("manufacturer"),
                stock.getInt("weight"),
                stock.getInt("price"),
                )

            stockList.add(newStock)
        }
    }
}