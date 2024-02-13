package com.project.deliveryapp

import com.project.deliveryapp.data.User
import com.project.deliveryapp.data.enum.Gender
import com.project.deliveryapp.retrofit.RetrofitClient
import com.project.deliveryapp.retrofit.RetrofitService
import com.project.deliveryapp.retrofit.dto.request.user.LoginDto
import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


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
    fun formatterTest() {
        val str = "2024-02-05T12:16:52.94985"
        val str2 = str.replace('T', ' ')

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSS")
        LocalDate.parse(str2, formatter)
        LocalDateTime.parse(str2, formatter)

    }

}