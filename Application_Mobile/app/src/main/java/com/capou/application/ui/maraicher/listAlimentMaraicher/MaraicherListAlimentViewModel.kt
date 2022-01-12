package com.capou.application.ui.maraicher.listAlimentMaraicher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MaraicherListAlimentViewModel : ViewModel() {

    private val _text =  MutableLiveData<String>().apply {
        value = " list Aliments Maraicher "
    }
    val text: LiveData<String> = _text
}