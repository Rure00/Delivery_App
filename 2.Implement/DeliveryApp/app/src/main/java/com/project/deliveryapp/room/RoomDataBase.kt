package com.project.deliveryapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.deliveryapp.room.data.MarketDataForRoom
import com.project.deliveryapp.room.data.UserDataForRoom

@Database(entities = [UserDataForRoom::class, MarketDataForRoom::class], version = 1, exportSchema = false)
abstract class RoomDataBase: RoomDatabase() {
    abstract val roomDao: RoomDataBaseDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDataBase? = null

        fun getInstance(context: Context): RoomDataBase {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDataBase::class.java,
                        "sleep history database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}