package com.project.deliveryapp.retrofit

import com.project.deliveryapp.retrofit.dto.LoginDto
import com.project.deliveryapp.retrofit.dto.UserDataDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

//Retrofit2 annotation note
//https://velog.io/@soyoung-dev/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C%EC%BD%94%ED%8B%80%EB%A6%B0-anch8mfi

interface RetrofitService {
    @FormUrlEncoded
    @POST("/user/login")
    fun tryLogin(
        @Body loginDto: LoginDto
    ): Call<UserDataDto>


}