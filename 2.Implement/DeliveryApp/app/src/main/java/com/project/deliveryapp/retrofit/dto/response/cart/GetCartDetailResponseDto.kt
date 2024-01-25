package com.project.deliveryapp.retrofit.dto.response.cart

import com.project.deliveryapp.data.Stock
import com.project.deliveryapp.retrofit.dto.ResponseDto
import com.project.deliveryapp.toLocalDateTime
import org.json.JSONObject

class GetCartDetailResponseDto(json: JSONObject): ResponseDto {
    val id: Long
    val marketId: Long
    val marketName: String
    val stockList: ArrayList<Stock>
    val stockCount: ArrayList<Int>
    val cost: Int

    init {
        val obj = json.getJSONObject("responseDto")
        stockList = ArrayList()
        stockCount = ArrayList()

        id = obj.getLong("id")
        val stockJson = obj.getJSONArray("stockList")
        val numJson = obj.getJSONArray("stockCount")

        marketId = obj.getLong("marketId")
        marketName = obj.getString("marketName")
        cost = obj.getInt("cost")

        for(index in 0..stockJson.length()) {
            val stock = stockJson.getJSONObject(index)
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
            stockCount.add(numJson.getInt(index))
        }
    }
}