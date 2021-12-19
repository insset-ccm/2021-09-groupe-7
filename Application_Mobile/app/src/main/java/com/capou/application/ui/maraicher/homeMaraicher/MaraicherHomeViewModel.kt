package com.capou.application.ui.maraicher.homeMaraicher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MaraicherHomeViewModel : ViewModel() {

    private val _text =  MutableLiveData<String>().apply {
        value = " Maraicher home"
    }
    val text: LiveData<String> = _text
}