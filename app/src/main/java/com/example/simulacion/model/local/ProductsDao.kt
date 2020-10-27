package com.example.simulacion.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.simulacion.model.network.ProductsItem

@Dao
interface ProductsDao {
    @Insert
    fun insertAllProducts(mList: ProductsItem)

    @Query("SELECT * FROM products_table ")
    fun shoAllProducts(): LiveData<List<ProductsItem>>

    @Query("SELECT * FROM products_table WHERE id =:mID")
    fun showOnProductsBy(mID : Int) : LiveData<ProductsItem>

}