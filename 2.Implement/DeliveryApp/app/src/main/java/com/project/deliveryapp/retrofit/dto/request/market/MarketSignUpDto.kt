package com.project.deliveryapp.retrofit.dto.request.market

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarketSignUpDto(
    val name: String,
    val loginId: String,
    val loginPwd: String,
    val phoneNumber: String,
    val latitude: Double,
    val longitude: Double,
    val description: String,
    val address: String,
): Parcelable {
    fun isOkay(): Boolean {
        return     name.isNotBlank()
                && loginId.isNotBlank()
                && loginPwd.isNotBlank()
                && phoneNumber.isNotBlank()
                && address.isNotBlank()
    }
}
