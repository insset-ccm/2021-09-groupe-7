package com.capou.application.ui.pickup_point.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capou.application.model.PickUpPoint
import com.capou.application.ui.pickup_point.repository.PickUpPointRepository

class PickUpPointViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val repository:PickUpPointRepository by lazy { PickUpPointRepository() }
    init {
   // repository.test()
    }

    fun getPickUpPoint(context: Context,title:String): MutableLiveData<ArrayList<PickUpPoint?>> {
        repository.getPickUpPoint(context,title)
        return repository.listPickupPoint
    }
}