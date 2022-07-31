package com.example.moneytracker.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moneytracker.data.model.Transaction

class HomeViewModel : ViewModel() {

    fun addTransaction(data : Transaction) {


    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text


}