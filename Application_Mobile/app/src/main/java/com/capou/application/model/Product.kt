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


data class AlimentModel(
    var nom:String,
    var images: String,
    var saison:HashMap<String,String>?
)

data class CommentModel(
    var id:String,
    var message:String,
    var date:String,
    var auteurs:String
)

data class UserModel(
    var nom:String,
    var prenom:String,
    var type:String
)