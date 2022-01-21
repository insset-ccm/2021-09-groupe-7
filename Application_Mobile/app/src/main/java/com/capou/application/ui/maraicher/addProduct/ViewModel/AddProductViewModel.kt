package com.capou.application.ui.maraicher.addProduct.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capou.application.ui.authentification.FirebaseAuthRepository
import com.capou.application.ui.maraicher.addProduct.repository.AddProductRepository

class AddProductViewModel: ViewModel() {

    private val repository : AddProductRepository by lazy { AddProductRepository() }

    init {

    }

  fun getUserInfo():MutableLiveData<String?>{
     repository.getUserData()
     return repository.userInfo
  }
    fun addProduct(product:String){
        repository.AddProduct(product)
    }
}