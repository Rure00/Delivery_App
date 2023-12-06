package com.project.deliveryapp.retrofit

import com.project.deliveryapp.data.UserData
import com.project.deliveryapp.data.enum.Gender
import com.project.deliveryapp.retrofit.dto.LoginDto
import com.project.deliveryapp.retrofit.dto.SignUpDto
import com.project.deliveryapp.retrofit.dto.UserDataDto
import com.project.deliveryapp.settings.SingletonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServerCommunicator: ServerCommunicatorInterface {
    private var service: RetrofitService = RetrofitClient.getInstance().create(RetrofitService::class.java)

    override suspend fun tryLogin(loginDto: LoginDto): Boolean {
        var flag = false
        var userDataDto: UserDataDto? = null

        return withContext(Dispatchers.IO) {

            service.tryLogin(loginDto).enqueue(object: Callback<UserDataDto> {
                override fun onResponse(call: Call<UserDataDto?>, response: Response<UserDataDto>) {
                    flag = true
                    userDataDto = response.body()
                }
                override fun onFailure(call: Call<UserDataDto?>, t: Throwable) {
                    flag = false
                }

            })

            if(flag) SingletonObject.setUserData(userDataDto!!.toUserData())

            flag
        }
    }

    override suspend fun requestCertification(randNum: Int, phoneNumber: String): Boolean {
        TODO("Not yet implemented")

        return true
    }

    override suspend fun trySignUp(signUpDto: SignUpDto): Boolean {
        TODO("Not yet implemented")

        return true
    }
}