package com.project.deliveryapp.settings

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.project.deliveryapp.activity.PaymentActivity
import com.project.deliveryapp.activity.StartActivity
import com.project.deliveryapp.data.User
import com.project.deliveryapp.room.RoomDataBase
import com.project.deliveryapp.room.data.LoginData
import com.project.deliveryapp.room.data.MarketIdForRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
    fun clearUserData(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val dao = RoomDataBase.getInstance(context).roomDao
            val data = dao.getUserData()!!
            dao.deleteUserData(data)
            user = null
        }

    }
    suspend fun getSavedUserData(context: Context): LoginData? {
        val dao = RoomDataBase.getInstance(context).roomDao

        return dao.getUserData()
    }
    suspend fun saveUserDataInRoom(context: Context): Boolean {
        val dao = RoomDataBase.getInstance(context).roomDao
        val ud = this.user!!
        val loginData = LoginData(
            ud.logInId,
            ud.loginPwd
        )
        dao.insertUserData(loginData)

        return true
    }
    suspend fun saveRecentMarket(context: Context, recentMarket: MarketIdForRoom): Boolean {
        val dao = RoomDataBase.getInstance(context).roomDao

        dao.insertRecentMarket(recentMarket)

        return true
    }
    suspend fun deleteRecentMarket(context: Context, recentMarket: MarketIdForRoom): Boolean {
        val dao = RoomDataBase.getInstance(context).roomDao

        dao.deleteRecentMarket(recentMarket)

        return true
    }
}