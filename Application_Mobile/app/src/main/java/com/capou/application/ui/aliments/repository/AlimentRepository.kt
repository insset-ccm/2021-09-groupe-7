package com.capou.application.ui.aliments.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.capou.application.model.AlimentModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.GenericTypeIndicator




class AlimentRepository {

    var element = arrayListOf<AlimentModel>()
    var default = MutableLiveData<ArrayList<AlimentModel>>()
    private var data = Firebase.database.reference


   // var query = data.child("aliments")
    init {
        var query = data.child("aliments")

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                    for (result in snapshot.children){
                        //val display = result.getValue(CommentModel::class.java)
                        //Log.d("DE","--> "+display)
                        Log.d("De"," "+result)
                        val genericTypeIndicator: GenericTypeIndicator<HashMap<String,String>?> =
                            object : GenericTypeIndicator<HashMap<String,String>?>() {}
                        var images = result.child("images").getValue(String::class.java).toString()
                        var nom = result.child("nom").getValue(String::class.java).toString()
                        var saison:HashMap<String,String>? = result.child("saison").getValue(genericTypeIndicator)
                        Log.d("De",images.toString())
                       element.add(AlimentModel(nom,images,saison))

                    }

                default.postValue(element)
                Log.d("Details"," "+element.size)


            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Tag",error.message)
            }
        })
    }
}