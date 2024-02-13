package com.project.deliveryapp.retrofit

import android.content.Context
import android.util.Log
import com.project.deliveryapp.retrofit.dto.ResponseDto
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
import com.project.deliveryapp.retrofit.dto.response.ErrorResponseDto
import com.project.deliveryapp.retrofit.dto.response.FeedbackMessages
import com.project.deliveryapp.retrofit.dto.response.NoBodyResponseDto
import com.project.deliveryapp.retrofit.dto.response.cart.GetCartDetailResponseDto
import com.project.deliveryapp.retrofit.dto.response.cart.GetCartsResponseDto
import com.project.deliveryapp.retrofit.dto.response.cart.SaveCartResponseDto
import com.project.deliveryapp.retrofit.dto.response.market.GetItemsResponseDto
import com.project.deliveryapp.retrofit.dto.response.market.MarketDetailResponseDto
import com.project.deliveryapp.retrofit.dto.response.market.MarketSignUpResponseDto
import com.project.deliveryapp.retrofit.dto.response.market.NearMarketResponseDto
import com.project.deliveryapp.retrofit.dto.response.order.GetOrderDetailResponseDto
import com.project.deliveryapp.retrofit.dto.response.order.GetOrderResponseDto
import com.project.deliveryapp.retrofit.dto.response.review.GetMyReviewsResponseDto
import com.project.deliveryapp.retrofit.dto.response.review.GetReviewsResponseDto
import com.project.deliveryapp.retrofit.dto.response.review.SaveReviewResponseDto
import com.project.deliveryapp.retrofit.dto.response.stock.GetStockResponseDto
import com.project.deliveryapp.retrofit.dto.response.user.LoginResponseDto
import com.project.deliveryapp.retrofit.dto.response.user.SignUpResponseDto
import com.project.deliveryapp.room.RoomDataBase
import com.project.deliveryapp.settings.SingletonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class ServerCommunicator: ServerCommunicatorInterface {
    private var service: RetrofitService = RetrofitClient.getInstance().create(RetrofitService::class.java)


    override suspend fun tryLogin(loginDto: LoginDto): ResponseResult
        = withContext(Dispatchers.IO) {
            val response = service.tryLogin(loginDto)

            var responseResult: ResponseResult

            when (response.code()) {
                in 200..299 -> {
                    response.body()!!.let {
                        val obj = JSONObject(it)
                        val flag = obj.getBoolean("flag")
                        val responseDto = LoginResponseDto(obj)

                        responseResult = ResponseResult(flag, responseDto)
                    }
                }
                in 400..499 -> {
                    val responseDto = ErrorResponseDto(FeedbackMessages.LOGIN_FAILED)
                    responseResult = ResponseResult(false, responseDto)
                }
                else -> {
                    throw Exception()
                }
            }

            responseResult
        }

    override suspend fun trySignUp(signUpDto: SignUpDto): ResponseResult
        = withContext(Dispatchers.IO) {
        val response = service.trySignup(signUpDto)

        var responseResult: ResponseResult

        when (response.code()) {
            in 200..299 -> {
                response.body()!!.let {
                    val obj = JSONObject(it)
                    val flag = obj.getBoolean("flag")
                    val responseDto = SignUpResponseDto(obj)

                    responseResult = ResponseResult(flag, responseDto)
                }
            }
            in 400..499 -> {
                val responseDto = ErrorResponseDto(FeedbackMessages.SIGNUP_FAILED)
                responseResult = ResponseResult(false, responseDto)
            }
            else -> {
                throw Exception()
            }
        }

        responseResult
    }

    override suspend fun marketSignUp(marketSignUpDto: MarketSignUpDto): ResponseResult
        = withContext(Dispatchers.IO) {
        val response = service.marketSignup(marketSignUpDto)

        var responseResult: ResponseResult

        when (response.code()) {
            in 200..299 -> {
                response.body()!!.let {
                    val obj = JSONObject(it)
                    val flag = obj.getBoolean("flag")
                    val responseDto = MarketSignUpResponseDto(obj)

                    responseResult = ResponseResult(flag, responseDto)
                }
            }
            in 400..499 -> {
                val responseDto = ErrorResponseDto(FeedbackMessages.SIGNUP_FAILED)
                responseResult = ResponseResult(false, responseDto)
            }
            else -> {
                throw Exception()
            }
        }

        responseResult
    }

    override suspend fun getMarketDetail(marketIdDto: MarketIdDto): ResponseResult
        = withContext(Dispatchers.IO) {
        val response = service.getMarketDetail(marketIdDto)

        var responseResult: ResponseResult

        when (response.code()) {
            in 200..299 -> {
                response.body()!!.let {
                    val obj = JSONObject(it)
                    val responseDto = MarketDetailResponseDto(obj)

                    responseResult = ResponseResult(true, responseDto)
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

        responseResult
    }

    override suspend fun getNearMarkets(nearMarketsDto: GetNearMarketsDto): ResponseResult
        = withContext(Dispatchers.IO) {
        val response = service.getNearMarkets(nearMarketsDto)
        var responseResult: ResponseResult

        when (response.code()) {
            in 200..299 -> {
                response.body()!!.let {
                    val obj = JSONObject(it)

                    val responseDto = NearMarketResponseDto(obj)
                    responseResult = ResponseResult(true, responseDto)
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

        responseResult
    }

    override suspend fun getItemsInMarket(marketIdDto: MarketIdDto): ResponseResult
    = withContext(Dispatchers.IO) {
        val response = service.getMarketItems(marketIdDto)
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

        responseResult
    }

    override suspend fun saveCart(saveCartDto: SaveCartDto): ResponseResult
        = withContext(Dispatchers.IO) {
            val response = service.saveCart(saveCartDto)
            var responseResult: ResponseResult

            when (response.code()) {
                in 200..299 -> {
                    response.body()!!.let {
                        val obj = JSONObject(it)
                        val flag = obj.getBoolean("flag")
                        val responseDto = SaveCartResponseDto(obj)

                        responseResult = ResponseResult(flag, responseDto)
                    }
                }
                else -> {
                    throw Exception()
                }
            }

            responseResult
        }

    override suspend fun removeCart(cartIdDto: CartIdDto): ResponseResult
        = withContext(Dispatchers.IO) {
            val response = service.removeCart(cartIdDto)
            var responseResult: ResponseResult

            when (response.code()) {
                in 200..299 -> {
                    response.body()!!.let {
                        val obj = JSONObject(it)
                        val flag = obj.getBoolean("flag")
                        val responseDto = NoBodyResponseDto()

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

            responseResult
        }

    override suspend fun getCartDetail(cartIdDto: CartIdDto): ResponseResult
        = withContext(Dispatchers.IO) {
            val response = service.getCartDetail(cartIdDto)
            var responseResult: ResponseResult

            when (response.code()) {
                in 200..299 -> {
                    response.body()!!.let {
                        val obj = JSONObject(it)
                        val flag = obj.getBoolean("flag")
                        val responseDto = GetCartDetailResponseDto(obj)

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

            responseResult
        }

    override suspend fun getMyCarts(userIdDto: UserIdDto): ResponseResult
        = withContext(Dispatchers.IO) {
            val response = service.getMyCart(userIdDto)
            var responseResult: ResponseResult

            when (response.code()) {
                in 200..299 -> {
                    response.body()!!.let {
                        val obj = JSONObject(it)
                        val flag = obj.getBoolean("flag")
                        val responseDto = GetCartsResponseDto(obj)

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

            responseResult
        }

    override suspend fun saveReview(saveReviewDto: SaveReviewDto): ResponseResult
        = withContext(Dispatchers.IO) {
            val response = service.saveReview(saveReviewDto)
            var responseResult: ResponseResult

            when (response.code()) {
                in 200..299 -> {
                    response.body()!!.let {
                        val obj = JSONObject(it)
                        val flag = obj.getBoolean("flag")
                        val responseDto = SaveReviewResponseDto(obj)

                        responseResult = ResponseResult(flag, responseDto)
                    }
                }
                else -> {
                    throw Exception()
                }
            }

            responseResult
        }

    override suspend fun getMyReviews(userIdDto: UserIdDto): ResponseResult
        = withContext(Dispatchers.IO) {
            val response = service.getMyReviews(userIdDto)
            var responseResult: ResponseResult

            when (response.code()) {
                in 200..299 -> {
                    response.body()!!.let {
                        val obj = JSONObject(it)
                        val flag = obj.getBoolean("flag")
                        val responseDto = GetMyReviewsResponseDto(obj)

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

            responseResult
        }

    override suspend fun getMarketReviews(marketIdDto: MarketIdDto): ResponseResult
        = withContext(Dispatchers.IO) {
            val response = service.getMarketReviews(marketIdDto)
            Log.d("JSON Response", "getMarketReviews: ${response.body()}")
            var responseResult: ResponseResult

            when (response.code()) {
                in 200..299 -> {
                    response.body()!!.let {
                        val obj = JSONObject(it)
                        val responseDto = GetReviewsResponseDto(obj)

                        responseResult = ResponseResult(true, responseDto)
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

            responseResult
        }

    override suspend fun removeReview(reviewIdDto: ReviewIdDto): ResponseResult
        = withContext(Dispatchers.IO) {
            val response = service.removeReview(reviewIdDto)
            var responseResult: ResponseResult

            when (response.code()) {
                in 200..299 -> {
                    response.body()!!.let {
                        val obj = JSONObject(it)
                        val flag = obj.getBoolean("flag")
                        val responseDto = NoBodyResponseDto()

                        responseResult = ResponseResult(flag, responseDto)
                    }
                }
                else -> {
                    throw Exception()
                }
            }

            responseResult
        }

    override suspend fun addStock(saveStockDto: SaveStockDto): ResponseResult
        = withContext(Dispatchers.IO) {
            val response = service.saveStock(saveStockDto)
            var responseResult: ResponseResult

            when (response.code()) {
                in 200..299 -> {
                    response.body()!!.let {
                        val obj = JSONObject(it)
                        val flag = obj.getBoolean("flag")
                        val responseDto = NoBodyResponseDto()

                        responseResult = ResponseResult(flag, responseDto)
                    }
                }
                in 400..499 -> {
                    val responseDto = ErrorResponseDto(FeedbackMessages.WRONG_PARAMETER)
                    responseResult = ResponseResult(false, responseDto)
                }
                else -> {
                    throw Exception()
                }
            }

            responseResult
        }

    override suspend fun getStockDetail(stockIdDto: StockIdDto): ResponseResult
        = withContext(Dispatchers.IO) {
            val response = service.getStockDetail(stockIdDto)
            var responseResult: ResponseResult

            when (response.code()) {
                in 200..299 -> {
                    response.body()!!.let {
                        val obj = JSONObject(it)
                        val flag = obj.getBoolean("flag")
                        val responseDto = GetStockResponseDto(obj)

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

            responseResult
        }

    override suspend fun getMyOrders(userIdDto: UserIdDto): ResponseResult
        = withContext(Dispatchers.IO) {
            val response = service.getMyOrders(userIdDto)
            var responseResult: ResponseResult

            when (response.code()) {
                in 200..299 -> {
                    response.body()!!.let {
                        val obj = JSONObject(it)
                        val flag = obj.getBoolean("flag")
                        val responseDto = GetOrderResponseDto(obj)

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

            responseResult
        }

    override suspend fun getOrderDetail(cartIdDto: CartIdDto): ResponseResult
        = withContext(Dispatchers.IO) {
            val response = service.getOrderDetail(cartIdDto)
            var responseResult: ResponseResult

            when (response.code()) {
                in 200..299 -> {
                    response.body()!!.let {
                        val obj = JSONObject(it)
                        val flag = obj.getBoolean("flag")
                        val responseDto = GetOrderDetailResponseDto(obj)

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

            responseResult
        }

    override suspend fun giveOrder(giveOrderDto: GiveOrderDto): ResponseResult
        = withContext(Dispatchers.IO) {
            val response = service.giveOrder(giveOrderDto)
            var responseResult: ResponseResult

            when (response.code()) {
                in 200..299 -> {
                    response.body()!!.let {
                        val obj = JSONObject(it)
                        val flag = obj.getBoolean("flag")
                        val responseDto = NoBodyResponseDto()

                        responseResult = ResponseResult(flag, responseDto)
                    }
                }
                else -> {
                    throw Exception()
                }
            }

            responseResult
        }


}