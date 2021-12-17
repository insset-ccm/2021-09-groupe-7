package com.capou.application.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is d Fragment"
    }

    private val _detail = MutableLiveData<String>().apply {

        val default ="Other"
       value = "This is details Fragment"
    }

    val text: LiveData<String> = _text

    val detail: LiveData<String> = _detail
}