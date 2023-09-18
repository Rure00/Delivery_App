package com.project.deliveryapp.data

import com.project.datavalidation_annotation.Val

data class UserData(
    val name: String,
    val nickname: String,
    val signInInfo: SignInInformation,
    val phoneNumber: String,
    //Todo: val gender: Gender


)
