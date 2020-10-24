package com.example.simulacion.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATA_BASE_NAME= "products_db"
@Database(entities=[ProductsEntity::class], version=1 )
abstract class ProductsDataBase : RoomDatabase(){

    //Método para ProductsDao
    abstract fun getProductDao(): ProductsDao

    companion object{
        @Volatile
        private var INSTANCE:ProductsDataBase?= null
        fun getDataBase(context: Context): ProductsDataBase{

            val tempInstance= INSTANCE
            if(tempInstance!= null){
                return tempInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(context,
                    ProductsDataBase::class.java,
                    DATA_BASE_NAME)
                    .build()
                INSTANCE = instance
                return instance
            }
        }

}
}