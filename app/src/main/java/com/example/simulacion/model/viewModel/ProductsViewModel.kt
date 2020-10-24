package com.example.simulacion.model.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.simulacion.model.local.ProductsDataBase
import com.example.simulacion.model.local.ProductsEntity
import com.example.simulacion.model.pojos.ProductsRepository

class ProductsViewModel (application: Application) : AndroidViewModel(application) { //sólo se hereda de la clase ViewModel sin contexto porque no es ROOM(androidViewmodel)
//Linea de arriba se modifica cuando después creo el ROOM

    private val mRepository: ProductsRepository
    //variable referencial al repositorio
    init{
        //se instancia el repositorio
        val productDao = ProductsDataBase.getDataBase(application).getProductDao()
         mRepository= ProductsRepository(productDao)
        //indico el método que traerá el repository
        mRepository.getDataFromServer( )
    }
    //primer fragmento
    fun exposeLiveDataFromServer(): LiveData<List<ProductsEntity>> {
        return mRepository.allProductsLiveData  //devuelve un Listado de productos observables

    }

    //no lo había hecho
//segundo fragmanto
    //Este elemento será observado por la vista cuando le pase el Id
    fun getOneFrutoskByID(id:Int): LiveData<ProductsEntity> {
        return mRepository.getOneProductsByID(id)
    }



}