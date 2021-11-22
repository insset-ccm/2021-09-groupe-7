package com.capou.application.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capou.application.ui.aliments.repository.AlimentRepository

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {

         value = "This is notifications Fragment1"
    }
    val text: LiveData<String> = _text
}