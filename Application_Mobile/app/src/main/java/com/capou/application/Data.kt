package com.capou.application

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.capou.application.api.Api
import com.capou.application.api.DetailsProduct
import com.capou.application.databinding.ActivityDataBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Data : AppCompatActivity() {
    private lateinit var binding :ActivityDataBinding
    val data = Firebase.database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

 /*  override fun getSupportActionBar(): ActionBar? {
        super.getSupportActionBar().setDisplayHomeAsUpEnabled(true)
        return super.getSupportActionBar()
    }*/

    override fun onStart() {
        super.onStart()
        Log.d("Informations:","start")
       // supportActionBar
        actionBar?.setTitle(R.string.message)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        //actionBar?.
        // add the parameter inside hthe getDetails("banana")
       /* Api.getDetailsProducts().getDetails("apple").enqueue(object : Callback<DetailsProduct?> {
            override fun onResponse(
                call: Call<DetailsProduct?>,
                response: Response<DetailsProduct?>
            ) {
                Log.d("Informations: ",response.body().toString())
            }

            override fun onFailure(call: Call<DetailsProduct?>, t: Throwable) {
                Log.d("Error:",t.message.toString())
            }
        })*/

    }
}