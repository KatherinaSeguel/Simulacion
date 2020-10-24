package com.example.simulacion.model.network


import com.google.gson.annotations.SerializedName

data class productsItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)