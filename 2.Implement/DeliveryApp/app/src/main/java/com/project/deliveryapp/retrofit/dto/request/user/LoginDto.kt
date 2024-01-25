package com.project.deliveryapp.retrofit.dto.request.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginDto(
    val loginId: String,
    val loginPwd: String
): Parcelable
