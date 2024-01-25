package com.project.deliveryapp.data

data class ItemOnBuy(
    val stock: Stock,
    var count: Int
) {
    companion object {
        fun toArray(stocks: ArrayList<Stock>, counts: ArrayList<Int>): ArrayList<ItemOnBuy> {
            val result = ArrayList<ItemOnBuy>()

            for (i in 0..stocks.size) {
                result.add(
                    ItemOnBuy(stocks[i], counts[i])
                )
            }

            return result
        }
    }

}
