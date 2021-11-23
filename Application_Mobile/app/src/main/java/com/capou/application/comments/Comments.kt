package com.capou.application.comments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capou.application.databinding.ActivityCommentsBinding

class Comments : AppCompatActivity() {

    private lateinit var binding: ActivityCommentsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}