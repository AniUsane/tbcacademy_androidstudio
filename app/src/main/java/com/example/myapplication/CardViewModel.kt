package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Date
import java.util.UUID

class CardViewModel: ViewModel() {

    private val _cardInfo = MutableLiveData<List<CardInfo>>()
    val cardInfo: LiveData<List<CardInfo>> = _cardInfo

    init {
        _cardInfo.value = listOf(
            CardInfo(id = UUID.randomUUID(), cardNumber = "43641345", holderName = "Ani Ani", expireDate = Date(), cardStatus = CardType.VISA),
            CardInfo(id = UUID.randomUUID(), cardNumber = "47574624", holderName = "Mari Mari", expireDate = Date(), cardStatus = CardType.MASTERCARD),
            CardInfo(id = UUID.randomUUID(), cardNumber = "34656345", holderName = "Nia Niafsffbiu", expireDate = Date(), cardStatus = CardType.VISA)
        )
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun addCard(card: CardInfo) {
        val currentList = _cardInfo.value?.toMutableList() ?: mutableListOf()
        currentList.add(card)
        _cardInfo.value = currentList
    }

    fun removeCard(card: CardInfo) {
        val currentList = _cardInfo.value?.toMutableList() ?: mutableListOf()
        currentList.remove(card)
        _cardInfo.value = currentList
    }
}