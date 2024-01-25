package com.project.deliveryapp.retrofit.dto.request.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUpDto(
    val name: String,
    val nickname: String,
    val loginId: String,
    val loginPwd: String,
    val phoneNumber: String,
    val gender: String,
    val address: String,
): Parcelable {
    fun isOkay(): Boolean {
        return     name.isNotBlank()
                && nickname.isNotBlank()
                && loginId.isNotBlank()
                && loginPwd.isNotBlank()
                && phoneNumber.isNotBlank()
                && gender.isNotBlank()
                && address.isNotBlank()
    }
}
