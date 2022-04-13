package com.capou.application.ui.maraicher.addProduct.repository

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import com.capou.application.R
import com.capou.application.ui.details.DetailsFragment
import com.capou.application.ui.maraicher.addProduct.view.AddProduct
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.MutableData
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class AddProductRepository {
    private val auth = Firebase.auth
    private val databaseRef = Firebase.database.reference.child("utilisateurs")
    var userInfo = MutableLiveData<String?>()

    fun getUserData(){
        var getUid = auth.currentUser?.uid.toString()
        //if(getUid.isNullOrEmpty()){
            var key = databaseRef.child("iU4wZkaPRWb20CHRqn4f2fkgH7F3").key
        if(!getUid.isNullOrEmpty()){
            databaseRef.child(getUid).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d("Debug","${snapshot}")
                    val result = snapshot.child("type").getValue(String::class.java)
                    if(!result.isNullOrEmpty()){
                        userInfo.postValue(result.toString())
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("Debug","${error.message}")
                }
            })
        }
            Log.d("Debuger","${key}")
        //}
    }


    fun AddProduct(product:String,lieu:String = "default"){
        var getUid = auth.currentUser?.uid.toString()
      //  if(!getUid.isNullOrEmpty()){
        val key = databaseRef.child(getUid).key
        if(!getUid.isEmpty()){
            val isKey = databaseRef.child(getUid).child("products").key
            Log.d("TAG", "AddProduct: ${isKey}")
            if( isKey == product){
                databaseRef.child(getUid).child("products").setValue(product)
            }
           val defaultKey = databaseRef.child(getUid).child("products").child(product).push().key
            databaseRef.child(getUid).child("products").child(product).child(defaultKey.toString()).setValue(lieu)

            // Add Point de ventes

            val isPoint = databaseRef.child(getUid).child("point_ventes").key
            Log.d("TAG", "AddProduct: ${isPoint}")
            if( isKey == product){
                databaseRef.child(getUid).child("point_ventes").setValue(lieu)
            }
            val point = databaseRef.child(getUid).child("point_ventes").child(lieu).push().key
            databaseRef.child(getUid).child("point_ventes").child(lieu).child(point.toString()).setValue(product)

        }
        Log.d("Debuger","${key}")

     //   }
    }


}