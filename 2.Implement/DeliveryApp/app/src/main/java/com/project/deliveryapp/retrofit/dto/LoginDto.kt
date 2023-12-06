package com.project.deliveryapp.retrofit.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginDto(
    val loginId: String,
    val loginPwd: String
): Parcelable
