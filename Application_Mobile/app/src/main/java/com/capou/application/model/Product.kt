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
    var saison:ArrayList<String?>
)

data class AlimentPointVentes(
    var name: String?,
    var address: ArrayList<String?>
)

data class CommentModel(
    var message:String,
    var date:String,
    var auteur:String
)

data class UserModel(
    var nom:String,
    var prenom:String,
    var type:String
)

data class Saison(
    var nom:String
)

data class PickUpPoint(
    var address:String?,
    var listMaraicher: ArrayList<List<String>?>
)

data class Schedule(
    var date:String
)

