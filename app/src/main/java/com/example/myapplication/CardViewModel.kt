package com.example.myapplication

import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CardViewModel:ViewModel() {
    private val _cardList = MutableLiveData<List<CardData>>()
    val cardList: LiveData<List<CardData>> get() = _cardList

    fun getData() {
        viewModelScope.launch {
            try {
                val cards = RetrofitClient.retrofit.getData()
                _cardList.postValue(cards)
            } catch (e: Exception) {
                d("CardViewModel", "Error: ${e.message}")
            }
        }
    }
}