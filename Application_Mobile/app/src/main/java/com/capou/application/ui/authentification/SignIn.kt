package com.capou.application.ui.authentification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.capou.application.MainActivity
import com.capou.application.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SignIn : AppCompatActivity() {

    // Identification

    private lateinit var binding : ActivitySignInBinding
    private val TAG = SignIn::class.java.simpleName;
    private lateinit var authentification : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater);
        setContentView(binding.root);

         this.authentification = Firebase.auth;
        val sign_in_email = binding.signinEmail.text.toString();




        binding.signinSignup.setOnClickListener{
            val intent = Intent(applicationContext,SignUp::class.java);
            startActivity(intent);
        }
    }

    override fun onStart(){
        binding.signinButton.setOnClickListener() {
            //var success = this.identification.login(this,binding.signinEmail.text.toString(), binding.signinPassword.text.toString());
            this.authentification.signInWithEmailAndPassword(binding.signinEmail.text.toString(), binding.signinPassword.text.toString())
                .addOnCompleteListener(this) { task ->
                    if(task.isSuccessful){
                        val intent = Intent(applicationContext,MainActivity::class.java);
                        startActivity(intent);
                    }else{

                    }
                }

        };



        super.onStart()
        val currentUSer = authentification.currentUser;
        if(currentUSer != null){

        }
        Log.d(TAG,currentUSer?.displayName.toString()+""+currentUSer?.email.toString())

    }
}