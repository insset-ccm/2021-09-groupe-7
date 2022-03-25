package com.capou.application.ui.authentification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
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
    private lateinit var viewModel : FirebaseAuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater);
        setContentView(binding.root);

        viewModel = ViewModelProvider(this).get(FirebaseAuthViewModel::class.java)
        val sign_in_email = binding.email.text.toString();




        binding.signinSignup.setOnClickListener{
            val intent = Intent(applicationContext,SignUp::class.java);
            startActivity(intent);
        }
    }

    override fun onStart(){
           super.onStart()
        binding.signinButton.setOnClickListener() {
            var email = binding.email.text.toString()
            var password = binding.password.text.toString()
            if(!email.isNullOrEmpty() && !password.isNullOrEmpty()){
                viewModel.signIn(email,password).observe(this,{

                    var checkSuccess = it.get("success")
                    if(checkSuccess == true) {
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                    }
                })
            }
        }
    }


    }
//}