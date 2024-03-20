package com.project.deliveryapp

import android.content.Context
import com.google.common.base.Joiner.on
import com.naver.maps.geometry.LatLng
import com.project.deliveryapp.data.Cart
import com.project.deliveryapp.data.ItemOnBuy
import com.project.deliveryapp.data.MarketData
import com.project.deliveryapp.data.Stock
import com.project.deliveryapp.data.User
import com.project.deliveryapp.data.enum.Gender
import com.project.deliveryapp.retrofit.RetrofitClient
import com.project.deliveryapp.retrofit.RetrofitService
import com.project.deliveryapp.retrofit.ServerCommunicator
import com.project.deliveryapp.retrofit.dto.ResponseResult
import com.project.deliveryapp.retrofit.dto.request.MarketIdDto
import com.project.deliveryapp.retrofit.dto.request.user.LoginDto
import com.project.deliveryapp.retrofit.dto.response.ErrorResponseDto
import com.project.deliveryapp.retrofit.dto.response.FeedbackMessages
import com.project.deliveryapp.retrofit.dto.response.market.GetItemsResponseDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.runners.MockitoJUnitRunner
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ExampleUnitTest {

    private var service: RetrofitService = RetrofitClient.getInstance().create(RetrofitService::class.java)


    @Test
    fun printJson() {

        println("start")

        runBlocking {
            println("Test: Print Json")
            val response = service.getMarketItems(MarketIdDto(6))
            var responseResult: ResponseResult

            when (response.code()) {
                in 200..299 -> {
                    response.body()!!.let {
                        val obj = JSONObject(it)
                        val flag = obj.getBoolean("flag")
                        val responseDto = GetItemsResponseDto(obj)

                        responseResult = ResponseResult(flag, responseDto)
                    }
                }
                in 400..499 -> {
                    val responseDto = ErrorResponseDto(FeedbackMessages.NOT_FOUND)
                    responseResult = ResponseResult(false, responseDto)
                }

                else -> {
                    throw Exception()
                }
            }

            println("ResponseResult: ${responseResult.result}, ${responseResult.response}")

            println("----------------------")
        }

        println("end")
    }


}