package com.capou.application.ui.authentification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.capou.application.MainActivity
import com.capou.application.databinding.ActivitySignUpBinding
import com.capou.application.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding
    private lateinit var viewModel: FirebaseAuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FirebaseAuthViewModel::class.java)

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
            var email = binding.email.text.toString()
            var password = binding.password.text.toString()
            var name = binding.lastname.text.toString()
            var firstname = binding.firstname.text.toString()

            viewModel.signUp(email,password,name,firstname,"user").observe(this,{
                var checkSuccess = it.get("success")
                if(checkSuccess==true){
                    val intent  = Intent(applicationContext,SignIn::class.java)
                    startActivity(intent)
                }
            })

        }
        super.onStart()
    }


    fun showMessage(message:String){
        Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show();
    }


}