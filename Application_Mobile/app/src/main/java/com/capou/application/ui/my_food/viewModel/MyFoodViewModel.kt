package com.capou.application.ui.my_food.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capou.application.ui.my_food.repository.MyFoodRepository

class MyFoodViewModel : ViewModel() {
    private val repository: MyFoodRepository by lazy { MyFoodRepository() }


    init {
        // Constructo
    }

    // Return the food list
    fun getMyFoodList(): MutableLiveData<ArrayList<String?>> {
        repository.getMyFoodList();
       return repository.myFoodList
    }
}