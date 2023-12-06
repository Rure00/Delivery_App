package com.project.deliveryapp.retrofit

import com.project.deliveryapp.retrofit.dto.LoginDto
import com.project.deliveryapp.retrofit.dto.SignUpDto

interface ServerCommunicatorInterface {

    suspend fun tryLogin(loginDto: LoginDto): Boolean

    suspend fun requestCertification(randNum: Int, phoneNumber: String): Boolean

    suspend fun trySignUp(signUpDto: SignUpDto): Boolean

}