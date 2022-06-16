package com.capou.application.ui.authentification.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.capou.application.MainActivity
import com.capou.application.databinding.ActivitySignInBinding
import com.capou.application.ui.authentification.viewModel.FirebaseAuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignIn : AppCompatActivity() {
    private var _authentification: FirebaseAuth = Firebase.auth
    // Identification

    private lateinit var binding : ActivitySignInBinding
    private val TAG = SignIn::class.java.simpleName;
    private lateinit var viewModel : FirebaseAuthViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ActionBar
        supportActionBar?.hide()


        binding = ActivitySignInBinding.inflate(layoutInflater);
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(FirebaseAuthViewModel::class.java)
        val sign_in_email = binding.email.text.toString()

        binding.signinSignup.setOnClickListener{
            val intent = Intent(applicationContext, SignUp::class.java);
            startActivity(intent);
        }
    }

    override fun onStart(){
           super.onStart()




        if(this._authentification.currentUser !=null){
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
        Toast.makeText(this,"${_authentification.currentUser}",Toast.LENGTH_LONG)
        Log.d("Connect","${_authentification.currentUser}")
        binding.signinButton.setOnClickListener() {
            Log.d("Connect","${_authentification.currentUser?.email} ${_authentification.currentUser?.uid}")
            var email = binding.email.text.toString()
            var password = binding.password.text.toString()
            if(!email.isNullOrEmpty() && !password.isNullOrEmpty()){
                viewModel.signIn(email,password).observe(this,{

                    var checkSuccess = it.get("success")
                    if(checkSuccess == true) {
                        var shared = getSharedPreferences("test", MODE_PRIVATE)
                        with (shared.edit()) {
                            putString("test",_authentification.currentUser?.uid.toString() )
                            apply()
                        }
                        Log.d("TAG", "onCreateView: "+shared.getString("test","default"))
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this,"L\' adresse mail ou/et mot de passe incorrecte(s).",Toast.LENGTH_LONG).show()
                    }
                })
            }
            else {
                Toast.makeText(this,"Certains champs sont vides. Veuillez les remplir",Toast.LENGTH_LONG).show()
            }
        }
    }


    }
//}