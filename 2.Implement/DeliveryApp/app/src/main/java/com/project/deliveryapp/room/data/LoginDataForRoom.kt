package com.project.deliveryapp.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoginData(
    @PrimaryKey
    val loginId: String,
    val loginPwd: String
)
