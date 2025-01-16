package com.example.myapplication

import android.text.InputType
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class DataViewModel: ViewModel() {

    private val _fields = MutableLiveData<List<FieldDataDto>>()
    val fields: LiveData<List<FieldDataDto>> get() = _fields

    fun loadFields(jsonResponse: String) {
        val gson = Gson()
        val fieldResponse = gson.fromJson(jsonResponse, ListData::class.java)
        _fields.postValue(fieldResponse.fields)
    }

    fun getInputType(keyboardType: String): Int {
        return if (keyboardType == "text") {
            InputType.TYPE_CLASS_TEXT
        } else {
            InputType.TYPE_CLASS_NUMBER
        }
    }



}