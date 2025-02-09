package com.example.myapplication.data.remote.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.myapplication.App
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object DataStoreManager {
    suspend fun saveValue(key: Preferences.Key<String>, value: String){
        App.context?.datastore?.edit { preference ->
            preference[key] = value
        }
    }

    fun readValue(key: Preferences.Key<String>): Flow<String>? {
        return App.context?.datastore?.data?.map{
            it[key] ?: ""
        }
    }

}