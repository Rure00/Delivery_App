package com.project.deliveryapp.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.project.deliveryapp.data.UserData

// At the top level of your kotlin file:
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


object SingletonObject {
    private var userData: UserData? = null

    public fun getUserData(): UserData? = userData
}