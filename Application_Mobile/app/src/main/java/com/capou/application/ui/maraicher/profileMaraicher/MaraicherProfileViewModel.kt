package com.capou.application.ui.maraicher.profileMaraicher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MaraicherProfileViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {

         value = "User Profile"
    }
    val text: LiveData<String> = _text
}