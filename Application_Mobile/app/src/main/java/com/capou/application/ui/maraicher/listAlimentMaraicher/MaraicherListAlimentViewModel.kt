package com.capou.application.ui.maraicher.listAlimentMaraicher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MaraicherListAlimentViewModel : ViewModel() {

   /* private val _text =  MutableLiveData<String>().apply {
        value = " list Aliments Maraicher "
    }
    val text: LiveData<String> = _text
*/
    private val MaraicherRepository :MaraicherRepository by lazy { MaraicherRepository() }

    fun saveAliment(fr_name : String, nom : String){

        MaraicherRepository.saveMaraicher(fr_name, nom)
    }
}