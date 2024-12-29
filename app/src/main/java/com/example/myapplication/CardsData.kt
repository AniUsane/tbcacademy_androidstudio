package com.example.myapplication

import java.util.Date
import java.util.UUID

data class CardsData(var id: UUID, var date:Date, var orderNumber:String, var trackingNumber:String,
    var quantity:Int, var total:String, var orderStatus:FilterStatus)
