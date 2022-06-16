package com.capou.application.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/*
data class DetailsProduct(
    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("family")
    val family:String,

    @Expose
    @SerializedName("order")
    val order:String,

    @Expose
    @SerializedName("nutritions")
    val nutritions : Nutrition
)

data class Nutrition(
    @Expose
    @SerializedName("carbohydrates")
    val carbohydrates: String,

    @Expose
    @SerializedName("protein")
    val protein:String,

    @Expose
    @SerializedName("calories")
    val calories:String,

    @Expose
    @SerializedName("fat")
    val fat:String,

    @Expose
    @SerializedName("sugar")
    val sugar:String
)*/





data class DetailsProduct(
    @Expose
    @SerializedName("data")
    val data : Data
)

data class Data(
    @Expose
    @SerializedName("aliments_info")
    val aliments_info: AlimentInfo,


    @Expose
    @SerializedName("fr_nom")
    val fr_nom:String,


    @Expose
    @SerializedName("description")
    val description:String,


    )

data class AlimentInfo(
    @Expose
    @SerializedName("calorie")
    val calorie: String,

    @Expose
    @SerializedName("proteine")
    val proteine:String,

    @Expose
    @SerializedName("lipides")
    val lipides:String,

    @Expose
    @SerializedName("glucides")
    val glucides:String,

    @Expose
    @SerializedName("vitamines")
    val vitamines:ArrayList<String>,

)

