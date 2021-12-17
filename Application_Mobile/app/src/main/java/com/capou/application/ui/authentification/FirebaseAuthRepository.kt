package com.capou.application.ui.authentification

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.capou.application.MainActivity
import com.capou.application.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseAuthRepository {
    private var _authentification: FirebaseAuth = Firebase.auth
    private var _database = Firebase.database.reference.child("utilisateurs")
    private var _data = MutableLiveData<String?>()
    var _dataSignIn = MutableLiveData<HashMap<String,Boolean>>()
    var _userInfo = MutableLiveData<String?>()
    private var infos = hashMapOf<String,Boolean>()


     fun SignUp(email: String, password:String, name:String, firstname:String, type:String){
        this._authentification.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                   // var iden = _database.child("utilisaters").push().key.toString()

                    val user = this._authentification.currentUser
                    var id = user?.uid.toString()
                    this._database.child(id).setValue(UserModel(name,firstname,type))
                        .addOnSuccessListener { it
                            infos.put("success",true)
                            this._dataSignIn.postValue(infos)
                        }
                        .addOnFailureListener {
                            infos.put("success",false)
                            this._dataSignIn.postValue(infos)
                        }
                    // updateUI(user)
                }
            }

            .addOnFailureListener {

            }
    }


    fun signIn(email: String,password: String){
        this._authentification.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                    infos.put("success",task.isSuccessful)
                   this._dataSignIn.postValue(infos)
            }
    }

    fun getUserInfo(){
        Log.d("TAG","info")

        this._database.child(this._authentification.currentUser?.uid.toString()).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

              var type =  snapshot.child("type").getValue(String::class.java).toString()
                Log.d("TAG"," "+snapshot+" "+type)
                    _userInfo.postValue(type)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}