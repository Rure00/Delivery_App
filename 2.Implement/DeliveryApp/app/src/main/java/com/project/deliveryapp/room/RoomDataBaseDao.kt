package com.project.deliveryapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.project.deliveryapp.room.data.LoginData
import com.project.deliveryapp.room.data.MarketIdForRoom

@Dao
interface RoomDataBaseDao {

    @Insert
    fun insertUserData(userDataForRoom: LoginData)
    @Delete
    fun deleteUserData(userDataForRoom: LoginData)
    @Update
    fun updateUserData(userDataForRoom: LoginData)
    @Query("SELECT * FROM LoginData")
    fun getUserData(): LoginData?

    @Insert
    fun insertRecentMarket(recentMarket: MarketIdForRoom)

    @Delete
    fun deleteRecentMarket(recentMarket: MarketIdForRoom)
    @Query("SELECT * FROM MarketIdForRoom")
    fun getRecentMarket(): List<MarketIdForRoom>?

}