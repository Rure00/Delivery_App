package com.project.deliveryapp.data

import com.project.deliveryapp.data.enum.Gender
import java.io.Serializable

data class User(
    val id: Long,
    val name: String,
    val nickname: String,
    val logInId: String,
    val loginPwd: String,
    val phoneNumber: String,
    val gender: Gender,
    val address: String,
): Serializable
