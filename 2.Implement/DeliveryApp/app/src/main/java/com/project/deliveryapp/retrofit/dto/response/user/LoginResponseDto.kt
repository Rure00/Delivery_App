package com.project.deliveryapp.retrofit.dto.response.user

import com.project.deliveryapp.data.User
import com.project.deliveryapp.data.enum.Gender
import com.project.deliveryapp.retrofit.dto.ResponseDto
import org.json.JSONObject

class LoginResponseDto(json: JSONObject): ResponseDto {
    val id: Long
    val name: String
    val nickname: String
    val loginId: String
    val loginPwd: String
    val phoneNumber: String
    val gender: String
    val address: String

    init {
        val obj = json.getJSONObject("responseDto")

        id = obj.getLong("id")
        name = obj.getString("name")
        nickname = obj.getString("nickname")
        loginId = obj.getString("loginId")
        loginPwd = obj.getString("loginPwd")
        phoneNumber = obj.getString("phoneNumber")
        gender = obj.getString("gender")
        address = obj.getString("address")
    }

    fun toUser(): User = User (
        id, name, nickname, loginId, loginPwd, phoneNumber, if(gender=="Male") Gender.Male else Gender.Female, address
    )

}