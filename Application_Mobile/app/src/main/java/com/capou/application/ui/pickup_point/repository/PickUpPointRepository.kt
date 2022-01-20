package com.capou.application.ui.pickup_point.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.capou.application.model.PickUpPoint
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class PickUpPointRepository {
        var element = arrayListOf<PickUpPoint?>()
        var horairesList = arrayListOf<List<String>?>()
        var listPickupPoint = MutableLiveData<ArrayList<PickUpPoint?>>()
        private var data = Firebase.database.reference


        fun tests(){
            Log.d("dev","start pickup point")
        }

        fun getPickUpPoint(context: Context,title:String){
            Log.d("Data","${title}")
            var query = data.child("pickup_point").child(title)

            query.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(result in snapshot.children){

                        //val display = result.getValue(CommentModel::class.java)
                        //Log.d("DE","--> "+display)
                        Log.d("Defaults.."," "+result)
                        var address = result.child("addresse").getValue(String::class.java)
                        var lists = result.child("ListsMaraichers")
                        Log.d("Defaults..","${lists}")

                        for(list in lists.children){
                            var getResult= list.child("Horaires").getValue(object : GenericTypeIndicator<List<String>>() {})
                            horairesList.add(getResult)
                        }
                       // var message = result.child("ListsMaraichers").getValue(List::class.java)

                         element.add(PickUpPoint(address,horairesList))
                        Log.d("Debugg","${result}")
                    Toast.makeText(context,"${result}",Toast.LENGTH_LONG).show()
                    }
                    listPickupPoint.postValue(element)
                    //  default.postValue(element)
                    //  Log.d("Details"," "+element.size)

                    /* val result = snap.getValue(CommentModel::class.java)
                     element.add(result)
                     Log.d("DE","--> "+element.size)*/
                }

                override fun onCancelled(error: DatabaseError) {
                    //
                    Log.d("Error..","${error.message}")
                }
            })
        }

}