package com.project.deliveryapp.data

import android.util.Log

data class Cart(
    private val marketData: MarketData
) {
    private val itemsOnCart: ArrayList<ItemOnBuy> = ArrayList()

    val expense: Long get() {
        var result: Long = 0
        itemsOnCart.forEach {
            result = it.count * (it.stock.price)
        }

        return result
    }

    val marketName = marketData.name
    val marketId = marketData.id

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
