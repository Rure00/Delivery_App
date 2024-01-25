package com.project.deliveryapp.data

data class CartDetail(
    val id: Long,
    val marketId: Long,
    val marketName: String,
    val itemsOnBuy: ArrayList<ItemOnBuy>,
    val cost: Int
)