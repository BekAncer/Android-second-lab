package com.example.myapplication
import com.google.gson.annotations.SerializedName

data class CatModel (
    @SerializedName("name")val name: String,
    @SerializedName("general_health") val health: String,
    @SerializedName("grooming")val grooming: String,
    @SerializedName("shedding")val shedding: String,
    @SerializedName("image_link") val image: String
    )