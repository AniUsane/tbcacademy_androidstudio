package com.example.myapplication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class CardClass(val id: UUID, val cardImage:Int, val productName:String, val quantity:Int, val orderStatus:String, val price:Float, var cardButton:String):Parcelable



