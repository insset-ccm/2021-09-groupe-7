package com.capou.application.ui.my_food.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MyFoodRepository {

    var myFoodList = MutableLiveData<ArrayList<String?>>()
    private var listAliment = ArrayList<String?>();
    private var path = Firebase.database.reference.child("utilisateurs")
    private var currentUser = Firebase.auth.currentUser?.uid.toString()


    init {

    }

    fun getMyFoodList(){
        if(!currentUser.isNullOrEmpty()){
            var query = path.child(currentUser).child("products");
            query.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d("Snapshot","${snapshot}")

                    for(list in snapshot.children){
                        var result = list.getValue(String::class.java)
                       listAliment.add(result)
                    }
                    myFoodList.postValue(listAliment)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("Error","${error}");
                }
            })
        }
    }
}