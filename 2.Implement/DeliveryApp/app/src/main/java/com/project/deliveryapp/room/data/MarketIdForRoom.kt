package com.project.deliveryapp.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MarketIdForRoom(
    @PrimaryKey
    val id: Long
) {

}
