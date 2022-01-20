package com.capou.application.ui.authentification

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capou.application.model.UserModel

class FirebaseAuthViewModel:ViewModel() {

    private val repository : FirebaseAuthRepository by lazy { FirebaseAuthRepository() }

    init {

    }

    fun signUp(email: String, password:String, name:String, firstname:String, type:String):MutableLiveData<HashMap<String,Boolean>>{
        repository.SignUp(email, password,name,firstname,type)
        return getInfo()
    }

    fun createUser(name:String, firstname:String, type:String):MutableLiveData<Boolean>{
       return  repository.createUser(name,firstname,type)
    }

     fun signIn(email: String,password: String):MutableLiveData<HashMap<String,Boolean>>{
        repository.signIn(email, password)
        return getInfo()
    }

    fun getUserInfo():MutableLiveData<String?>{
        repository.getUserInfo()
        return repository._userInfo
    }

    private fun getInfo():MutableLiveData<HashMap<String,Boolean>>{
        return  repository._dataSignIn
    }

}