package com.example.myapplication

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow

private const val DATA_STORE_FILE_NAME = "user_info.pb"

//Extension for Context to access DataStore for UserInfo
val Context.userInfoStore: DataStore<UserInfo> by dataStore(
    fileName = DATA_STORE_FILE_NAME,
    serializer = UserInfoSerializer
)


class UserInfoRepository(
    private val userInfoStore: DataStore<UserInfo>) {

    val userInfo: Flow<UserInfo> = userInfoStore.data

    //Saves user information and updates the old one
    suspend fun saveUser(firstName: String, lastName: String, email: String) {
        userInfoStore.updateData { currentInfo ->
            currentInfo.toBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email).build()
        }
    }
}