package com.capou.application.ui.maraicher.profileMaraicher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MaraicherProfileViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {

         value = "This is Profile Fragment1"
    }
    val text: LiveData<String> = _text
}