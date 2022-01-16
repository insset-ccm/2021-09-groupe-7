package com.capou.application.comments.Repository

import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.capou.application.model.CommentModel
import com.capou.application.model.Saison
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CommentRepository(private val title: String) {
    var element = arrayListOf<CommentModel>()
    var default = MutableLiveData<ArrayList<CommentModel>>()
    private var data = Firebase.database.reference
    private var auth = Firebase.auth

    var query = data.child("commentaire").child(title)
    init {
        Log.d("Data","${title}")
        var query = data.child("commentaire").child("apple")

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(result in snapshot.children){

                        //val display = result.getValue(CommentModel::class.java)
                        //Log.d("DE","--> "+display)
                        Log.d("De"," "+result)
                        var auteur = result.child("auteur").getValue(String::class.java)
                        var message = result.child("message").getValue(String::class.java)
                        var date = result.child("date").getValue(String::class.java)
                        Log.d("De",auteur.toString())
                        element.add(CommentModel(message.toString(),date.toString(),auteur.toString()))

                }
                default.postValue(element)
                Log.d("Details"," "+element.size)

                /* val result = snap.getValue(CommentModel::class.java)
                 element.add(result)
                 Log.d("DE","--> "+element.size)*/
            }

            override fun onCancelled(error: DatabaseError) {
              //
                Log.d("Error","${error.message}")
            }
        })
    }

    fun writeComment(product:String,message:String,date:String,auteurs:String){
        val create_key = query.push().key
        //val user = auth.currentUser?.uid.toString()
        val user = "YeFvh42yPzL7ftL4vUa89cml0D43"

        data.child("commentaire").orderByValue().equalTo("Jane Doe").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("Data","${snapshot}")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Errorddd","${error.message}")
            }
        })


        data.child("commentaire").orderByValue().equalTo("Jane Doe").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("Other","${snapshot}")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Other","${error.message}")
            }
        })


        val date = SimpleDateFormat("dd/MM/yyyy HH:mm")
        val comment = CommentModel(message,date.format(Date()),"Jane Doe")
        if(create_key!=null){
           // query.child("banana").orderByValue().startAt("Jane")
            query.child(create_key).setValue(comment)
                .addOnSuccessListener {
                    Log.d("Error","Success")
            }
                .addOnFailureListener {
                    Log.d("Error","${it.message}")
                }
        }
    }
}