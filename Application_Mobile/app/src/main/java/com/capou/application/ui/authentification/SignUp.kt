package com.capou.application.ui.authentification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
    private var defaultType:String = "Utilisateur"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FirebaseAuthViewModel::class.java)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupSignin.setOnClickListener {
            val intent = Intent(applicationContext,SignIn::class.java);
            startActivity(intent);
        }
        var list = arrayOf("Utilisateur","Maraîcher")
        binding.signinPasswordC.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                Log.d("Debug","${selectedItem}")
                defaultType = selectedItem.toString()
            } // to close the onItemSelected

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
        val array = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        array.setDropDownViewResource(android.R.layout.simple_spinner_item)
        binding.signinPasswordC.adapter = array

    }



    override fun onStart() {


        binding.signupButton.setOnClickListener {
            showMessage("Start to create the user")
            var email = binding.email.text.toString()
            var password = binding.password.text.toString()
            var name = binding.lastname.text.toString()
            var firstname = binding.firstname.text.toString()
            Toast.makeText(applicationContext,"${defaultType}",Toast.LENGTH_LONG).show()
            viewModel.signUp(email,password,name,firstname,defaultType).observe(this,{
                var checkSuccess = it.get("success")
                Log.d("Debug","${it.get("success")} ${it}")
              //  if(checkSuccess==true){
                    viewModel.createUser(name,firstname,defaultType)
                    val intent  = Intent(applicationContext,SignIn::class.java)
                    startActivity(intent)
             //  }
            })

        }
        super.onStart()
    }


    fun showMessage(message:String){
        Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show();
    }


}