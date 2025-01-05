package com.example.myapplication

import java.util.Date
import java.util.UUID

data class MessagesData(val id: UUID, val message: String, val time:Date, val rightAligned:Boolean)
