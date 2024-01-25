package com.project.deliveryapp.data

import android.util.Log
import com.project.deliveryapp.retrofit.dto.request.MarketIdDto
import com.project.deliveryapp.settings.SingletonObject

data class Cart(
    private val marketData: MarketData
) {
    private val itemsOnCart: ArrayList<ItemOnBuy> = ArrayList()

    val expense: Int get() {
        var result: Int = 0
        itemsOnCart.forEach {
            result = it.count * (it.stock.price)
        }

        return result
    }

    val marketName
        get() = marketData.name
    val marketId: Long
        get() = marketData.id
    val userId = SingletonObject.getUserId()
    val itemIdList: List<Long>
        get() {
            val list = mutableListOf<Long>()
            itemsOnCart.forEach {
                list.add(it.stock.id)
            }
            return list
        }
    val countList: List<Int>
        get() {
            val list = mutableListOf<Int>()
            itemsOnCart.forEach {
                list.add(it.count)
            }
            return list
        }

    fun add(item: ItemOnBuy) {
        var isDuplicated = false
        for(index in itemsOnCart.indices) {
            if(itemsOnCart[index].stock == item.stock) {
                isDuplicated = true
                itemsOnCart[index].count += item.count
            }
        }

        Log.d("Shopping", "itemsOnCart.contains(item): $isDuplicated")
        if(!isDuplicated) itemsOnCart.add(item)

    }
    fun delete(item: Stock): Boolean {
        var index = -1
        itemsOnCart.forEachIndexed { i ,it ->
            if(it.stock == item) {
                index = i
                return@forEachIndexed
            }
        }

        return if(index == -1) {
            false
        } else {
            itemsOnCart.removeAt(index)
            true
        }
    }
    fun length() = itemsOnCart.size
}
