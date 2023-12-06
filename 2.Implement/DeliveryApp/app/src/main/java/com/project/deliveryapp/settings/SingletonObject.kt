package com.project.deliveryapp.settings

import android.content.Context
import android.util.Log
import com.project.deliveryapp.data.UserData
import com.project.deliveryapp.room.RoomDataBase
import com.project.deliveryapp.room.data.MarketDataForRoom
import com.project.deliveryapp.room.data.UserDataForRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.IllegalStateException


object SingletonObject {

    private var userData: UserData? = null

    fun getUserId()= userData!!.id
    fun getUserNickname() = userData!!.nickname

    fun setUserData(userData: UserData) {
        if(this.userData != null) {
            throw IllegalStateException("UserData is set twice...")
        }
        this.userData = userData
    }
    suspend fun getSavedUserData(context: Context): UserDataForRoom? {
        val dao = RoomDataBase.getInstance(context).roomDao

        return dao.getUserData()
    }
    suspend fun saveUserDataInRoom(context: Context): Boolean {
        val dao = RoomDataBase.getInstance(context).roomDao
        val ud = this.userData!!
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