package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val avatar:Int?,
    val firstName:String,
    val lastName:String,
    val about:String?,
    val activationStatus:Int

)