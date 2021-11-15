package com.capou.application.ui.authentification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.capou.application.MainActivity
import com.capou.application.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding
    private lateinit var authentification: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authentification = Firebase.auth

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupSignin.setOnClickListener {
            val intent = Intent(applicationContext,SignIn::class.java);
            startActivity(intent);
        }

    }

    override fun onStart() {
        binding.signupButton.setOnClickListener {
            showMessage("Start to create the user")
            this.authentification.createUserWithEmailAndPassword(binding.signupEmail.text.toString(), binding.signupPassword.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = authentification.currentUser
                       // updateUI(user)
                        val intent = Intent(applicationContext,SignIn::class.java);
                        startActivity(intent);
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(applicationContext,"Authentication failed "+task.exception?.message,Toast.LENGTH_LONG).show();

                    //    updateUI(null)
                    }
                }
        }
        super.onStart()
    }


    fun showMessage(message:String){
        Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show();
    }


}