package com.project.deliveryapp.view_model

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.naver.maps.geometry.LatLng
import com.project.deliveryapp.activity.MainActivity
import com.project.deliveryapp.data.Cart
import com.project.deliveryapp.data.CartDetail
import com.project.deliveryapp.data.ItemOnBuy
import com.project.deliveryapp.data.MarketData
import com.project.deliveryapp.data.Order
import com.project.deliveryapp.data.Review
import com.project.deliveryapp.data.Stock
import com.project.deliveryapp.data.User
import com.project.deliveryapp.data.dto.MarketReviewDto
import com.project.deliveryapp.data.dto.NearMarketDto
import com.project.deliveryapp.data.dto.SimpleCartDto
import com.project.deliveryapp.data.enum.Gender
import com.project.deliveryapp.data.enum.State
import com.project.deliveryapp.retrofit.ServerCommunicator
import com.project.deliveryapp.retrofit.dto.request.CartIdDto
import com.project.deliveryapp.retrofit.dto.request.MarketIdDto
import com.project.deliveryapp.retrofit.dto.request.ReviewIdDto
import com.project.deliveryapp.retrofit.dto.request.UserIdDto
import com.project.deliveryapp.retrofit.dto.request.cart.SaveCartDto
import com.project.deliveryapp.retrofit.dto.request.market.GetNearMarketsDto
import com.project.deliveryapp.retrofit.dto.request.review.SaveReviewDto
import com.project.deliveryapp.retrofit.dto.request.user.LoginDto
import com.project.deliveryapp.retrofit.dto.response.ErrorResponseDto
import com.project.deliveryapp.retrofit.dto.response.cart.GetCartDetailResponseDto
import com.project.deliveryapp.retrofit.dto.response.cart.GetCartsResponseDto
import com.project.deliveryapp.retrofit.dto.response.cart.SaveCartResponseDto
import com.project.deliveryapp.retrofit.dto.response.market.GetItemsResponseDto
import com.project.deliveryapp.retrofit.dto.response.market.MarketDetailResponseDto
import com.project.deliveryapp.retrofit.dto.response.market.NearMarketResponseDto
import com.project.deliveryapp.retrofit.dto.response.order.GetOrderResponseDto
import com.project.deliveryapp.retrofit.dto.response.review.GetMyReviewsResponseDto
import com.project.deliveryapp.retrofit.dto.response.review.GetReviewsResponseDto
import com.project.deliveryapp.retrofit.dto.response.user.LoginResponseDto
import com.project.deliveryapp.room.RoomDataBase
import com.project.deliveryapp.room.data.MarketDataForRoom
import com.project.deliveryapp.settings.SingletonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class MainViewModel: ViewModel() {
    private var communicator: ServerCommunicator = ServerCommunicator()
    var curMarketId: Long = 0
    var curCartId: Long = 0
    var curOrderId: Long = 0

    suspend fun getRecentMarketInfo(context: Context): List<MarketDataForRoom>? {
        val dao = RoomDataBase.getInstance(context).roomDao
        return withContext(Dispatchers.IO) {
            dao.getRecentMarket()
        }
    }











    suspend fun getMarketData(context: Context, marketId: Long): MarketData? {
        val response = runCatching{ communicator.getMarketDetail(MarketIdDto(marketId)) }
            .onFailure {
                withContext(Dispatchers.Main) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "나중에 다시 이용해주세요.", Toast.LENGTH_SHORT).show()
                    }
                }
                return null
            }.getOrNull()!!


        return if(response.result) {
            val obj = response.response as MarketDetailResponseDto
            MarketData(
                obj.id, obj.name, obj.score, obj.phoneNumber, obj.address, obj.description,
                LatLng(obj.latitude, obj.longitude)
            )
        } else {
            val error = response.response as ErrorResponseDto
            withContext(Dispatchers.Main) {
                Toast.makeText(context, error.comment, Toast.LENGTH_SHORT).show()
            }
            null
        }
    }
    suspend fun getReviews(context: Context, marketIt: Long): ArrayList<MarketReviewDto> {
        val result = ArrayList<MarketReviewDto>()

        val response = runCatching{ communicator.getMarketReviews(MarketIdDto(marketIt)) }
            .onFailure {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "나중에 다시 이용해주세요.", Toast.LENGTH_SHORT).show()
                }
                return result
            }.getOrNull()!!

        if(response.result) {
            val obj = response.response as GetReviewsResponseDto
            for(i in 0..obj.idList.size) {
                val ele = MarketReviewDto(
                    obj.idList[i],
                    obj.commentList[i],
                    obj.scoreList[i],
                    obj.dateList[i],
                )

                result.add(ele)
            }
        } else {
            val error = response.response as ErrorResponseDto
            Toast.makeText(context, error.comment, Toast.LENGTH_SHORT).show()
        }

        return result
    }
    suspend fun getNearMarkets(context: Context, curLocation: LatLng, distance: Int = 3000): ArrayList<NearMarketDto> {
        val result = ArrayList<NearMarketDto>()
        val requestDto = GetNearMarketsDto(
            curLocation.latitude, curLocation.longitude, distance
        )
        val response = runCatching{ communicator.getNearMarkets(requestDto) }
            .onFailure {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "나중에 다시 이용해주세요.", Toast.LENGTH_SHORT).show()
                }
                return result
            }.getOrNull()!!

        if(response.result) {
            val obj = response.response as NearMarketResponseDto
            for(i in 0..obj.id.size) {
                val ele = NearMarketDto(
                    obj.id[i],
                    obj.name[i],
                    obj.latitude[i],
                    obj.longitude[i]
                )

                result.add(ele)
            }
        } else {
            val error = response.response as ErrorResponseDto
            Toast.makeText(context, error.comment, Toast.LENGTH_SHORT).show()
        }

        return result
    }

    suspend fun getStocks(context: Context, marketIt: Long): ArrayList<Stock> {
        var result = ArrayList<Stock>()

        val response = runCatching{ communicator.getItemsInMarket(MarketIdDto(marketIt)) }
            .onFailure {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "나중에 다시 이용해주세요.", Toast.LENGTH_SHORT).show()
                }
                return result
            }.getOrNull()!!

        if(response.result) {
            val obj = response.response as GetItemsResponseDto
            result = obj.stockList
        } else {
            val error = response.response as ErrorResponseDto
            Toast.makeText(context, error.comment, Toast.LENGTH_SHORT).show()
        }

        return result
    }
    suspend fun saveCart(context: Context, cart: Cart): Long? {
        val requestDto = SaveCartDto(
            cart.userId, cart.marketId, cart.marketName,
            cart.itemIdList, cart.countList, cart.expense
        )
        val response = runCatching{ communicator.saveCart(requestDto) }
            .onFailure {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "나중에 다시 이용해주세요.", Toast.LENGTH_SHORT).show()
                }
                return null
            }.getOrNull()!!

        return  if(response.result) (response.response as SaveCartResponseDto).id
                else {
                    val error = response.response as ErrorResponseDto
                    Toast.makeText(context, error.comment, Toast.LENGTH_SHORT).show()
                    return null
                }
    }
    suspend fun saveReview(context: Context, review: Review): Boolean {
        val requestDto = SaveReviewDto(
            review.userId,
            review.marketId,
            review.marketName,
            review.comment,
            review.score
        )

        val response = runCatching{ communicator.saveReview(requestDto) }
            .onFailure {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "나중에 다시 이용해주세요.", Toast.LENGTH_SHORT).show()
                }
                return false
            }.getOrNull()!!

        return  if(response.result) true
                else {
                    val error = response.response as ErrorResponseDto
                    Toast.makeText(context, error.comment, Toast.LENGTH_SHORT).show()
                    false
                }
    }
    suspend fun getMyCarts(context: Context): ArrayList<SimpleCartDto> {
        val result = ArrayList<SimpleCartDto>()
        val response = runCatching{ communicator.getMyCarts(UserIdDto(SingletonObject.getUserId())) }
            .onFailure {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "나중에 다시 이용해주세요.", Toast.LENGTH_SHORT).show()
                }
                return result
            }.getOrNull()!!

        if(response.result) {
            val obj = response.response as GetCartsResponseDto
            for(i in 0..obj.idList.size) {
                val ele = SimpleCartDto(
                    obj.idList[i],
                    obj.marketNameList[i],
                    obj.costList[i]
                )

                result.add(ele)
            }
        } else {
            val error = response.response as ErrorResponseDto
            Toast.makeText(context, error.comment, Toast.LENGTH_SHORT).show()
        }

        return  result
    }
    suspend fun getCartDetail(context: Context, cartId: Long): CartDetail? {
        var result: CartDetail? = null
        val response = runCatching{ communicator.getCartDetail(CartIdDto(cartId)) }
            .onFailure {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "나중에 다시 이용해주세요.", Toast.LENGTH_SHORT).show()
                }
                return result
            }.getOrNull()!!

        if(response.result) {
            val obj = response.response as GetCartDetailResponseDto

            result = CartDetail(
                cartId, obj.marketId, obj.marketName, ItemOnBuy.toArray(obj.stockList, obj.stockCount), obj.cost
            )
        } else {
            val error = response.response as ErrorResponseDto
            Toast.makeText(context, error.comment, Toast.LENGTH_SHORT).show()
        }

        return  result
    }
    suspend fun removeCart(context: Context, cartId: Long): Boolean {
        val response = runCatching{ communicator.removeCart(CartIdDto(cartId)) }
            .onFailure {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "나중에 다시 이용해주세요.", Toast.LENGTH_SHORT).show()
                }
                return false
            }.getOrNull()!!

        return  if(response.result) true
        else {
            val error = response.response as ErrorResponseDto
            Toast.makeText(context, error.comment, Toast.LENGTH_SHORT).show()
            false
        }
    }
    suspend fun getMyOrder(context: Context): ArrayList<Order> {
        val result = ArrayList<Order>()
        val response = runCatching{ communicator.getMyOrders(UserIdDto(SingletonObject.getUserId())) }
            .onFailure {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "나중에 다시 이용해주세요.", Toast.LENGTH_SHORT).show()
                }
                return result
            }.getOrNull()!!

        if(response.result){
            val obj = response.response as GetOrderResponseDto
            for(i in 0..obj.idList.size) {
                val state = when(obj.stateList[i]) {
                    "OnReception" -> State.OnReception
                    "OnDelivery" -> State.OnDelivery
                    "Delivered" -> State.Delivered
                    else -> throw Exception()
                }
                val ele = Order(
                    obj.idList[i], obj.marketNameList[i],obj.costList[i], state, obj.dateList[i]
                )

                result.add(ele)
            }
        } else {
            val error = response.response as ErrorResponseDto
            Toast.makeText(context, error.comment, Toast.LENGTH_SHORT).show()
        }

        return result
    }
    suspend fun getMyReviews(context: Context): ArrayList<Review> {
        val result = ArrayList<Review>()
        val userId = SingletonObject.getUserId()
        val response = runCatching{ communicator.getMyReviews(UserIdDto(userId)) }
            .onFailure {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "나중에 다시 이용해주세요.", Toast.LENGTH_SHORT).show()
                }
                return result
            }.getOrNull()!!

        if(response.result){
            val obj = response.response as GetMyReviewsResponseDto
            val nickname = SingletonObject.getUserNickname()
            for(i in 0..obj.idList.size) {
                val ele = Review(
                    obj.idList[i], userId, obj.marketIdList[i], nickname, obj.marketNameList[i],
                    obj.commentList[i], obj.scoreList[i], obj.dateList[i]
                )

                result.add(ele)
            }
        } else {
            val error = response.response as ErrorResponseDto
            Toast.makeText(context, error.comment, Toast.LENGTH_SHORT).show()
        }

        return result
    }
    suspend fun removeReview(context: Context, review: Review): Boolean {
        val response = runCatching{ communicator.removeReview(ReviewIdDto(review.id)) }
            .onFailure {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "나중에 다시 이용해주세요.", Toast.LENGTH_SHORT).show()
                }
                return false
            }.getOrNull()!!

        return if(response.result) true
                else {
                    val error = response.response as ErrorResponseDto
                    Toast.makeText(context, error.comment, Toast.LENGTH_SHORT).show()
                    false
                }
    }
}