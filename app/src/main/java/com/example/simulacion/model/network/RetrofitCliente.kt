package com.example.simulacion.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitCliente{
    companion object{

        private const val  URL_BASE = "http://my-json-server.typicode.com/Himuravidal/FakeAPIdata/"


        fun getRetrofitClient(): ProductsAPI {

            val mRetrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return  mRetrofit.create(ProductsAPI::class.java) // devuelve la unión de la Interface con el cliente retrofit

        }
    }

}