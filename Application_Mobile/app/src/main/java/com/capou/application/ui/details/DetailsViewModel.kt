package com.capou.application.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capou.application.api.Api
import com.capou.application.api.DetailsRoom
import java.util.concurrent.Callable
import javax.security.auth.callback.Callback

class DetailsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is d Fragment"
    }

    private val _detail = MutableLiveData<String>().apply {
       value = "This is details Fragment"
    }

    val text: LiveData<String> = _text

    val detail: LiveData<String> = _detail
}