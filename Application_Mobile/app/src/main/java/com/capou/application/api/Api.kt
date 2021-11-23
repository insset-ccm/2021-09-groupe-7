package com.capou.application.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {
    // Call the api

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.fruityvice.com/api/fruit/banana")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()))
        .build()

    fun getDetailsProducts(): Details = retrofit.create(Details::class.java)

}
