package com.capou.application.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.capou.application.MainActivity
import com.capou.application.databinding.ActivitySplachScreenBinding
import com.capou.application.ui.authentification.view.SignIn

class SplachScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplachScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplachScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }

    override fun onStart() {
        super.onStart()
        Handler().postDelayed({
        val intent = Intent(this,SignIn::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
}