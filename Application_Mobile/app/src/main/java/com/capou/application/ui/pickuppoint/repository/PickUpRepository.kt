package com.capou.application.ui.pickuppoint.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class PickUpRepository {
    private val auth = Firebase.auth
    private val databaseRef = Firebase.database.reference.child("utilisateurs")
    var userInfo = MutableLiveData<HashMap<String?, List<String?>>>()




    fun listPickUpPoint(){
        val getUid = auth.currentUser?.uid.toString()
        //  if(!getUid.isNullOrEmpty()){
        val key = databaseRef.child(getUid).key
        if(!getUid.isEmpty()){

                //point de vente
            databaseRef.child(getUid).child("point_ventes").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val listData = HashMap<String?, List<String?>>()
                    Log.d("Debug", "onDataChange: ${snapshot}")
                    for(list in snapshot.children){
                        // key
                        val key = ArrayList<String?>()
                        for(result in list.children){
                            val value = result.getValue(String::class.java)
                            key.add(value)
                        }

                        listData["${list.key}"] = key
                        Log.d("TAG", "onDataChange: ${listData}")
                        userInfo.postValue(listData)
                    }
                    Log.d("TAG", "onDataChange: ${userInfo}")
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

        }
        Log.d("Debuger","${key}")

        //   }
    }


}