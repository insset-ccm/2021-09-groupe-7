package com.capou.application.api

import com.google.gson.annotations.SerializedName

data class DetailsRoom(
    @SerializedName("fat")
    val fat: String,
)


data class DetailsProduct(
    val images: String,
    val  nom: String,
    val saison : Array<String>
)


data class Info(
    val images: String,
    val  nom: String,
    val saison : List<Season>
)

data class Season(
    val saison: String
)
