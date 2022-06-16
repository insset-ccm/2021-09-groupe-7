package com.capou.application.ui.maraicher.addProduct.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capou.application.ui.maraicher.addProduct.repository.AddProductRepository

class AddProductViewModel: ViewModel() {

    private val repository : AddProductRepository by lazy { AddProductRepository() }

    init {

    }

  fun getUserInfo():MutableLiveData<String?>{
     repository.getUserData()
     return repository.userInfo
  }

    fun getListPick():MutableLiveData<List<String?>>{
        repository.getPickUp()
        return repository.pickup
    }
    fun addProduct(product:String,lieu:String){
        repository.AddProduct(product,lieu)
    }

    fun addProductMarai(product:String,lieu:String){
        repository.addProductMarai(product,lieu)
    }
}