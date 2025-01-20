package com.example.myapplication

import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName

@JsonClass(generateAdapter = true)
data class MessageInfo(
    val id:Int,
    val image:String,
    val owner:String,
    @SerialName("last_message")
    val lastMessage:String,
    @SerialName("last_active")
    val lastActive:String,
    @SerialName("unread_messages")
    val unreadMessages:Int? = null,
    @SerialName("is_typing")
    val isTyping:Boolean,
    @SerialName("laste_message_type")
    val lastMessageType:String
)
