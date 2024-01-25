package com.project.deliveryapp.settings

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.project.deliveryapp.activity.PaymentActivity
import com.project.deliveryapp.activity.StartActivity
import com.project.deliveryapp.data.User
import com.project.deliveryapp.room.RoomDataBase
import com.project.deliveryapp.room.data.MarketDataForRoom
import com.project.deliveryapp.room.data.UserDataForRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.IllegalStateException


object SingletonObject {

    private var user: User? = null

    fun getUserId()= user!!.id
    fun getUserNickname() = user!!.nickname



    fun setUserData(user: User) {
        if(this.user != null) {
            throw IllegalStateException("UserData is set twice...")
        }
        this.user = user
    }
    suspend fun getSavedUserData(context: Context): UserDataForRoom? {
        val dao = RoomDataBase.getInstance(context).roomDao

        return dao.getUserData()
    }
    suspend fun saveUserDataInRoom(context: Context): Boolean {
        val dao = RoomDataBase.getInstance(context).roomDao
        val ud = this.user!!
        val userDataForRoom = UserDataForRoom(
            ud.logInId,
            ud.loginPwd
        )
        dao.insertUserData(userDataForRoom)

        return true
    }
    suspend fun saveRecentMarket(context: Context, recentMarket: MarketDataForRoom): Boolean {
        val dao = RoomDataBase.getInstance(context).roomDao

        dao.insertRecentMarket(recentMarket)

        return true
    }
    suspend fun deleteRecentMarket(context: Context, recentMarket: MarketDataForRoom): Boolean {
        val dao = RoomDataBase.getInstance(context).roomDao

        dao.deleteRecentMarket(recentMarket)

        return true
    }
}