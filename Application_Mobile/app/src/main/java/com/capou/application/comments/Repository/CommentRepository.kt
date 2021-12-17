package com.capou.application.comments.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.capou.application.model.CommentModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CommentRepository {

    var element = arrayListOf<CommentModel>()
    var default = MutableLiveData<ArrayList<CommentModel>>()
    private var data = Firebase.database.reference


    var query = data.child("commentaire")
    init {
        var query = data.child("commentaire")

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(snap in snapshot.children){
                    for (result in snap.children){
                        //val display = result.getValue(CommentModel::class.java)
                        //Log.d("DE","--> "+display)
                        Log.d("De"," "+result)
                        var auteur = result.child("auteur").getValue(String::class.java)
                        var message = result.child("message").getValue(String::class.java)
                        Log.d("De",auteur.toString())
                        element.add(CommentModel("0",message.toString(),"23-11-2020",auteur.toString()))

                    }
                }
                default.postValue(element)
                Log.d("Details"," "+element.size)



                /* val result = snap.getValue(CommentModel::class.java)
                 element.add(result)
                 Log.d("DE","--> "+element.size)*/

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}