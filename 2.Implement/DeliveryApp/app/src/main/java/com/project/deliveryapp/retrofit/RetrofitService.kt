package com.project.deliveryapp.retrofit

import android.util.JsonToken
import com.project.deliveryapp.retrofit.dto.request.CartIdDto
import com.project.deliveryapp.retrofit.dto.request.MarketIdDto
import com.project.deliveryapp.retrofit.dto.request.ReviewIdDto
import com.project.deliveryapp.retrofit.dto.request.StockIdDto
import com.project.deliveryapp.retrofit.dto.request.UserIdDto
import com.project.deliveryapp.retrofit.dto.request.cart.SaveCartDto
import com.project.deliveryapp.retrofit.dto.request.market.GetNearMarketsDto
import com.project.deliveryapp.retrofit.dto.request.market.MarketSignUpDto
import com.project.deliveryapp.retrofit.dto.request.order.GiveOrderDto
import com.project.deliveryapp.retrofit.dto.request.review.SaveReviewDto
import com.project.deliveryapp.retrofit.dto.request.stock.SaveStockDto
import com.project.deliveryapp.retrofit.dto.request.user.LoginDto
import com.project.deliveryapp.retrofit.dto.request.user.SignUpDto
import com.project.deliveryapp.retrofit.dto.response.ErrorResponseDto
import com.project.deliveryapp.retrofit.dto.response.user.LoginResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

//Retrofit2 annotation note
//https://velog.io/@soyoung-dev/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C%EC%BD%94%ED%8B%80%EB%A6%B0-anch8mfi

interface RetrofitService {

    @GET("test/print-string")
    suspend fun test(): Response<String>

    @POST("/user/login")
    suspend fun tryLogin(
        @Body loginDto: LoginDto
    ): Response<String>

    @POST("/user/signup")
    suspend fun trySignup(
        @Body signUpDto: SignUpDto
    ): Response<String>

    @POST("/market/signup")
    suspend fun marketSignup(
        @Body marketSignUpDto: MarketSignUpDto
    ): Response<String>

    @POST("/market/get")
    suspend fun getMarketDetail(
        @Body marketIdDto: MarketIdDto
    ): Response<String>

    @POST("/market/near")
    suspend fun getNearMarkets(
        @Body nearMarketsDto: GetNearMarketsDto
    ): Response<String>

    @POST("/market/items")
    suspend fun getMarketItems(
        @Body marketIdDto: MarketIdDto
    ): Response<String>

    @POST("/cart/save")
    suspend fun saveCart(
        @Body saveCartDto: SaveCartDto
    ): Response<String>

    @POST("/cart/remove")
    suspend fun removeCart(
        @Body cartIdDto: CartIdDto
    ): Response<String>

    @POST("/cart/detail")
    suspend fun getCartDetail(
        @Body cartIdDto: CartIdDto
    ): Response<String>

    @POST("/cart/get")
    suspend fun getMyCart(
        @Body userIdDto: UserIdDto
    ): Response<String>

    @POST("/review/save")
    suspend fun saveReview(
        @Body saveReviewDto: SaveReviewDto
    ): Response<String>

    @POST("/review/mine")
    suspend fun getMyReviews(
        @Body userIdDto: UserIdDto
    ): Response<String>

    @POST("/review/get")
    suspend fun getMarketReviews(
        @Body marketIdDto: MarketIdDto
    ): Response<String>

    @POST("/review/get")
    suspend fun removeReview(
        @Body reviewIdDto: ReviewIdDto
    ): Response<String>

    @POST("/stock/add")
    suspend fun saveStock(
        @Body saveStockDto: SaveStockDto
    ): Response<String>

    @POST("/stock/get")
    suspend fun getStockDetail(
        @Body stockIdDto: StockIdDto
    ): Response<String>

    @POST("/order/get")
    suspend fun getMyOrders(
        @Body userIdDto: UserIdDto
    ): Response<String>

    @POST("/order/detail")
    suspend fun getOrderDetail(
        @Body orderIdDto: CartIdDto
    ): Response<String>

    @POST("/order/give-order")
    suspend fun giveOrder(
        @Body giveOrderDto: GiveOrderDto
    ): Response<String>
}