package com.project.deliveryapp.retrofit.dto

import android.os.Parcelable
import com.project.deliveryapp.data.UserData
import com.project.deliveryapp.data.enum.Gender
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDataDto (
    private val id: Int,
    val name: String,
    val nickname: String,
    val logInId: String,
    val loginPwd: String,
    val phoneNumber: String,
    val gender: Gender,
    val address: String,
): Parcelable {
    fun toUserData()
    = UserData(
        id,
        name,
        nickname,
        logInId,
        loginPwd,
        phoneNumber,
        gender,
        address
    )
}