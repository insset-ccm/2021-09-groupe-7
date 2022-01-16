package com.capou.application.ui.maraicher.listAlimentMaraicher

import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.security.KeyStore

class MaraicherRepository {

    private lateinit var database : DatabaseReference

    fun saveMaraicher( fr_name : String, nom : String){

        database = FirebaseDatabase.getInstance().getReference("aliment")
        val Aliment = ProduitModel(fr_name, nom )
        val key = database.child("aliment").push().key
        if(key != null)
        database.child(key).setValue(Aliment).addOnSuccessListener {
            Log.d("succes","susses2")
         //   Toast.makeText(this,"enregis", Toast.LENGTH_LONG).show()
            // Toast.makeText(this,"Successfully Saved", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Log.d("error", "errorStr")
          //  Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
        }
    }
}