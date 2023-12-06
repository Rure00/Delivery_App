package com.project.deliveryapp.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.deliveryapp.data.UserData
import com.project.deliveryapp.data.enum.Gender

@Entity
data class UserDataForRoom(
    @PrimaryKey
    val loginId: String,
    val loginPwd: String
)
