package com.example.simulacion.model.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsAPI {
    // los product
    @GET("products/")
    fun getDataFromApi() : Call<products>

    //Buscar un fruto
    @GET("products/")
    fun getDataFromApiCorutines(@Query("produ") mProducts:String): retrofit2.Response<products>

}