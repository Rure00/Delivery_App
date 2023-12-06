package com.project.deliveryapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.project.deliveryapp.data.enum.Gender
import com.project.deliveryapp.room.data.UserDataForRoom
import kotlinx.parcelize.Parcelize
import java.io.Serializable

data class UserData(
    val id: Int,
    val name: String,
    val nickname: String,
    val logInId: String,
    val loginPwd: String,
    val phoneNumber: String,
    val gender: Gender,
    val address: String,
): Serializable
