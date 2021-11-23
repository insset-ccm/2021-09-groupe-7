package com.capou.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.capou.application.databinding.ActivityDataBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Data : AppCompatActivity() {
    private lateinit var binding :ActivityDataBinding

    val data = Firebase.database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun onStart() {
        super.onStart()
        Log.d("Bonjour",data.toString())
        //val issuccess = data.setValue("Bonjour").isSuccessful
        // Log.d("Bon",issuccess.toString())
        data.setValue("Hello -")
            .addOnSuccessListener {
                Log.d("Bonj","yes")

            }
            .addOnFailureListener { ex : Exception ->
                Log.d("TAG", ex.toString())

            }

        data.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                Log.d("Bon",snapshot.toString())
            }

            override fun onCancelled(error: DatabaseError) {

                Log.d("Bon", error.message)
            }

        })
    }
}