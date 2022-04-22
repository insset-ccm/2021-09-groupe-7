package com.capou.application.ui.authentification.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.capou.application.MainActivity
import com.capou.application.databinding.ActivitySignUpBinding
import com.capou.application.ui.authentification.viewModel.FirebaseAuthViewModel

class SignUp : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding
    private lateinit var viewModel: FirebaseAuthViewModel
    private var defaultType:String = "Utilisateur"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        viewModel = ViewModelProvider(this).get(FirebaseAuthViewModel::class.java)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupSignin.setOnClickListener {
            val intent = Intent(applicationContext, SignIn::class.java);
            startActivity(intent);
        }

        val list = arrayOf("Utilisateur","Maraîcher")
        binding.signinPasswordC.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                defaultType = selectedItem
            } // to close the onItemSelected

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        val array = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, list)
        array.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        binding.signinPasswordC.adapter = array
    }



    override fun onStart() {
        super.onStart()
        binding.signupButton.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            val name = binding.lastname.text.toString()
            val firstname = binding.firstname.text.toString()
            if(!email.isEmpty()
                && !password.isEmpty()
                && !name.isEmpty()
                && !firstname.isEmpty()){
                viewModel.signUp(email,password,name,firstname,defaultType).observe(this,{
                    val checkSuccess = it.get("success")
                    if(checkSuccess==true){
                    viewModel.createUser(name,firstname,defaultType)
                        val intent  = Intent(applicationContext,MainActivity::class.java)
                          startActivity(intent)
                          finish()
                     }
                })
            }else{
                Toast.makeText(this,"Certains champs sont vides. Veuillez les remplir", Toast.LENGTH_LONG).show()
            }

        }

    }




}