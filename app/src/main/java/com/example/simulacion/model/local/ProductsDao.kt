package com.example.simulacion.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductsDao {
    @Insert
    fun insertAllProducts(mList: List<ProductsEntity>)

    @Query("SELECT * FROM products_table ")
    fun shoAllProducts(): LiveData<List<ProductsEntity>>

    @Query("SELECT * FROM products_table WHERE id =:mID")
    fun showOnProductsBy(mID : Int) : LiveData<ProductsEntity>

}