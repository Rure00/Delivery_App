package com.project.deliveryapp

import com.project.deliveryapp.data.UserData
import com.project.deliveryapp.data.enum.Gender
import com.project.deliveryapp.retrofit.RetrofitClient
import com.project.deliveryapp.retrofit.RetrofitService
import com.project.deliveryapp.retrofit.dto.LoginDto
import org.junit.Test


class ExampleUnitTest {

    //private val context: Context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun addition_isCorrect() {
        val greeting: (String, Int)-> String =  { name, age ->
            val result = "Hello I'm $name, $age age year old "
            println(result)

            result
        }

        val result: String =greeting("Sung", 25)
    }

    @Test
    fun testForUserDataLoad() {
        val ud: UserData = UserData(
            0,
            "Sung", "Rure",
            "asnjkd", "6344",
            "0102213414", Gender.Male, "sadang"
        )

        val service: RetrofitService = RetrofitClient.getInstance().create(RetrofitService::class.java)


        val loginDto = LoginDto(
            "asnjkd", "6344"
        )

        service.tryLogin(loginDto)

    }

}