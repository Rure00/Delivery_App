package com.project.deliveryapp.data

import com.project.deliveryapp.data.enum.State
import java.time.LocalDateTime

data class Order (
    val id: Long,
    val marketName: String,
    val cost: Long,
    var state: State,
    val date: LocalDateTime
)