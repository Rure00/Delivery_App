package com.project.deliveryapp.retrofit

import android.content.Context
import com.project.deliveryapp.retrofit.dto.ResponseResult
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

interface ServerCommunicatorInterface {

    suspend fun tryLogin(loginDto: LoginDto): ResponseResult
    suspend fun trySignUp(signUpDto: SignUpDto): ResponseResult
    suspend fun marketSignUp(marketSignUpDto: MarketSignUpDto): ResponseResult
    suspend fun getMarketDetail(marketIdDto: MarketIdDto): ResponseResult
    suspend fun getNearMarkets(nearMarketsDto: GetNearMarketsDto): ResponseResult
    suspend fun getItemsInMarket(marketIdDto: MarketIdDto): ResponseResult
    suspend fun saveCart(saveCartDto: SaveCartDto): ResponseResult
    suspend fun removeCart(cartIdDto: CartIdDto): ResponseResult
    suspend fun getCartDetail(cartIdDto: CartIdDto): ResponseResult
    suspend fun getMyCarts(userIdDto: UserIdDto): ResponseResult
    suspend fun saveReview(saveReviewDto: SaveReviewDto): ResponseResult
    suspend fun getMyReviews(userIdDto: UserIdDto): ResponseResult
    suspend fun getMarketReviews(marketIdDto: MarketIdDto): ResponseResult
    suspend fun removeReview(reviewIdDto: ReviewIdDto): ResponseResult
    suspend fun addStock(saveStockDto: SaveStockDto): ResponseResult
    suspend fun getStockDetail(stockIdDto: StockIdDto): ResponseResult
    suspend fun getMyOrders(userIdDto: UserIdDto): ResponseResult
    suspend fun getOrderDetail(cartIdDto: CartIdDto): ResponseResult
    suspend fun giveOrder(giveOrderDto: GiveOrderDto): ResponseResult

}