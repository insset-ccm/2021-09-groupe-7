package com.capou.application.ui.pickuppoint.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capou.application.ui.pickuppoint.repository.PickUpRepository

class PickUpViewModel : ViewModel() {

    private val repository : PickUpRepository by lazy { PickUpRepository() }

    init {

    }

    fun getListPickUpPoint():MutableLiveData<HashMap<String?, List<String?>>>{
        repository.listPickUpPoint()
        return repository.userInfo
    }
}