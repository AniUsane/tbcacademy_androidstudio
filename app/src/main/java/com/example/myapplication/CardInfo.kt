package com.example.myapplication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date
import java.util.UUID

enum class CardType{
    VISA,
    MASTERCARD
}

@Parcelize
data class CardInfo(val id: UUID, val cardNumber:String, val holderName:String, val expireDate:Date, val ccv:String = "111", val cardStatus:CardType):Parcelable
