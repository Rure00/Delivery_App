package com.project.deliveryapp.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.naver.maps.geometry.LatLng
import com.project.deliveryapp.data.Cart
import com.project.deliveryapp.data.ItemOnBuy
import com.project.deliveryapp.data.MarketData
import com.project.deliveryapp.data.Review
import com.project.deliveryapp.data.Stock
import com.project.deliveryapp.retrofit.dto.ReviewResponseDto
import com.project.deliveryapp.room.RoomDataBase
import com.project.deliveryapp.room.data.MarketDataForRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class MainViewModel: ViewModel() {
    //private var service: RetrofitService = RetrofitClient.getInstance().create(RetrofitService::class.java)
    private var curMarket: MarketData? = null

    suspend fun getRecentMarketInfo(context: Context): List<MarketDataForRoom>? {
        val dao = RoomDataBase.getInstance(context).roomDao
        return withContext(Dispatchers.IO) {
            dao.getRecentMarket()
        }
    }

    fun saveCurMarket(market: MarketData) {
        curMarket = market
    }
    fun getCurMarket(): MarketData = curMarket!!

    suspend fun getMarketData(): MarketData {
        //return service.getMarketData()

        return MarketData(
            1, "삼성", 3.5f, "01012314", "사당동", "hi", LatLng(37.5, 123.7)
        )
    }
    suspend fun getReviews(): ReviewResponseDto {
        //TODO: Locate a my review on first in List<Review>.

        return ReviewResponseDto(true, null)
    }
    suspend fun getNearMarkets(distance: Int = 3000): List<MarketData>? {
        //TODO: get Markets near by
        return null
    }

    suspend fun getStocks(): Array<Stock> {
        //return service.getMarketData()

        val stock = Stock(
            null, 3, "삼다수", LocalDateTime.now() , "삼성", 500f, 1000
        )

        return arrayOf(stock)
    }
    fun saveCart(cart: Cart): Boolean {
        val result = false

        CoroutineScope(Dispatchers.IO).launch {

        }


        return result;
    }
    fun saveReview(review: Review) {
        CoroutineScope(Dispatchers.IO).launch {

        }
    }
    suspend fun getCarts(): ArrayList<Cart> {

        val market = getMarketData()

        val cart = Cart(market)
        val stocks = getStocks()

        val item = ItemOnBuy(
            stocks[0], 3
        )

        cart.add(item)

        val result = ArrayList<Cart>()
        result.add(cart)

        return result
    }

    fun removeCart(cart: Cart) {
        CoroutineScope(Dispatchers.IO).launch {

        }
    }
}