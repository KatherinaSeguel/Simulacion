package com.example.simulacion.model.network

import retrofit2.Call
import retrofit2.http.GET

interface ProductsAPI {
    //Detalle de los product
    @GET("details")
    fun getDataFromApi() : Call<productsItem>
}