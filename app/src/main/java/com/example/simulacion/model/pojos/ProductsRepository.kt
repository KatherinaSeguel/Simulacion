package com.example.simulacion.model.pojos

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.simulacion.model.local.ProductsDao
import com.example.simulacion.model.local.ProductsEntity
import com.example.simulacion.model.network.ProductsAPI
import com.example.simulacion.model.network.RetrofitCliente
import com.example.simulacion.model.network.products
import com.example.simulacion.model.network.productsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsRepository (private val productDao: ProductsDao) {
    private val retroService  = RetrofitCliente.getRetrofitClient()
 val allProductsLiveData = productDao.shoAllProducts()


    //La vieja confiable
    fun getDataFromServer() {
        val call = retroService.getDataFromApi()
        call.enqueue(object : Callback<products> {

            override fun onFailure(call: Call<products>, t: Throwable) {
                Log.e("Repository",t.message.toString())

            }

            override fun onResponse(call: Call<products>, response: Response<products>)
            {
                when(response.code()){
                    //***se cambia***  in 200..299 -> mLiveData.postValue(response.body())
                    in 200..299 -> CoroutineScope(Dispatchers.IO).launch {
                        response.body()?.let {

                            Log.d("Info",it.toString())
                            productDao.insertAllProducts(converter(it))

                        }
                    }
                    in 300..399 -> Log.d("ERROR 300",response.errorBody().toString())
                    in 400..499 -> Log.d("ERROR 400",response.errorBody().toString())
                }
            }


        }) //llamadas asincronas
    }

    fun converter(list: List<productsItem>):List<ProductsEntity>{

        var listProducts:MutableList<ProductsEntity> = mutableListOf<ProductsEntity>()
        list.map {
            listProducts.add(ProductsEntity(it.id,it.image,it.name,it.price))
        }
        return listProducts
    }
    fun getOneProductsByID(mID : Int): LiveData<ProductsEntity> {
        return productDao.showOnProductsBy(mID)
    }

}

private fun <T> Call<T>.enqueue(callback: Callback<products>) {

}


