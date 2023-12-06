package com.project.deliveryapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.project.deliveryapp.room.data.MarketDataForRoom
import com.project.deliveryapp.room.data.UserDataForRoom

@Dao
interface RoomDataBaseDao {

    @Insert
    fun insertUserData(userDataForRoom: UserDataForRoom)
    @Delete
    fun deleteUserData(userDataForRoom: UserDataForRoom)
    @Update
    fun updateUserData(userDataForRoom: UserDataForRoom)
    @Query("SELECT * FROM UserDataForRoom")
    fun getUserData(): UserDataForRoom?

    @Insert
    fun insertRecentMarket(recentMarket: MarketDataForRoom)

    @Delete
    fun deleteRecentMarket(recentMarket: MarketDataForRoom)
    @Query("SELECT * FROM MarketDataForRoom")
    fun getRecentMarket(): List<MarketDataForRoom>?

}