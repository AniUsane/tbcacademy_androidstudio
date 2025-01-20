package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class MessageViewModel: ViewModel() {
    private val _messages = MutableLiveData<List<MessageInfo>?>()
    val messages: MutableLiveData<List<MessageInfo>?> = _messages


    fun jsonParsing(){
        val jsonString = """[
                    {
                    "id":1,
                    "image":"https://www.alia.ge/wp-content/uploads/2022/09/grisha.jpg",
                    "owner":"გრიშა ონიანი",
                    "last_message":"თავის ტერიტორიას ბომბავდა",
                    "last_active":"4:20 PM",
                    "unread_messages":3,
                    "is_typing":false,
                    "laste_message_type":"text"
                    },
                    {
                    "id":2,
                    "image":null,
                    "owner":"ჯემალ კაკაურიძე",
                    "last_message":"შემოგევლე",
                    "last_active":"3:00 AM",
                    "unread_messages":0,
                    "is_typing":true,
                    "laste_message_type":"voice"
                    },
                    {
                    "id":3,
                    "image":"https://i.ytimg.com/vi/KYY0TBqTfQg/hqdefault.jpg",
                    "owner":"გურამ ჯინორია",
                    "last_message":"ცოცხალი ვარ მა რა ვარ შე.. როდის იყო კვტარი ტელეფონზე ლაპარაკობდა",
                    "last_active":"1:00 ",
                    "unread_messages":0,
                    "is_typing":false,
                    "laste_message_type":"file"
                    },
                    {
                    "id":4,
                    "image":"",
                    "owner":"კაკო წენგუაშვილი",
                    "last_message":"ადამიანი რო მოსაკლავად გაგიმეტებს თანაც ქალი ის დასანდობი არ არი",
                    "last_active":"1:00 PM",
                    "unread_messages":0,
                    "is_typing":false,
                    "laste_message_type":"text"
                    }
                    ]
                    """

        val moshi: Moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, MessageInfo::class.java)
        val adapter = moshi.adapter<List<MessageInfo>>(type)
        val parsedData = adapter.fromJson(jsonString)
        _messages.value = parsedData

    }

}
