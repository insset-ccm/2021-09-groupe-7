package com.capou.application.ui.authentification

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.R
import androidx.appcompat.app.AlertDialog
import com.capou.application.MainActivity
import com.capou.application.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.math.sign

class SignIn : AppCompatActivity() {

    // Identification

    private lateinit var binding : ActivitySignInBinding
    private val TAG = SignIn::class.java.simpleName;

    private lateinit var authentification : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater);
        setContentView(binding.root);

        authentification = Firebase.auth;
        val sign_in_email = binding.signinEmail.text.toString();


        binding.signinButton.setOnClickListener {

            val toast = Toast.makeText(applicationContext, "Message from the input: " +binding.signinPassword.text.toString()+" "+binding.signinEmail.text.toString(), Toast.LENGTH_LONG);
            toast.show();
            Log.d(TAG, "Mesage: $sign_in_email");
            Log.d(TAG,binding.signinEmail.text.toString());

                authentification.signInWithEmailAndPassword(binding.signinEmail.text.toString(), binding.signinPassword.text.toString())
                    .addOnCompleteListener(this) { task ->
                        Log.d(TAG,binding.signinEmail.text.toString())
                        Log.d(TAG,binding.signinPassword.text.toString())
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val user = authentification.currentUser
                            // updateUI(user)
                            Log.d(TAG,user?.displayName.toString());
                            val intent = Intent(applicationContext,MainActivity::class.java);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                            val builder = AlertDialog.Builder(applicationContext)
                            builder.setMessage("Erreur de connexion")
                            builder.setPositiveButton("OK",
                                DialogInterface.OnClickListener { dialog, id ->
                                    // FIRE ZE MISSILES!
                                    Log.d(TAG,dialog.toString()+""+id);
                                })
                            builder.create()

                        }
                    }


        };

        binding.signinSignup.setOnClickListener{
            val intent = Intent(applicationContext,SignUp::class.java);
            startActivity(intent);
        }
    }



    override fun onStart() {
        super.onStart()
        val currentUSer = authentification.currentUser;
        if(currentUSer != null){

        }
        Log.d(TAG,currentUSer?.displayName.toString()+""+currentUSer?.email.toString())

    }
}