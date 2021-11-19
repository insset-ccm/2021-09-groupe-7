package com.capou.application.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


//Retrofit data
data class ProductDetails(
    @Expose
    @SerializedName("food")
    val food: String,

    @Expose
    @SerializedName("benefits")
    val benefits: String
)
