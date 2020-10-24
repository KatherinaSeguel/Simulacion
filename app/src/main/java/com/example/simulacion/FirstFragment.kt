package com.example.simulacion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simulacion.model.local.ProductsEntity
import com.example.simulacion.model.viewModel.ProductsAdapter
import com.example.simulacion.model.viewModel.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment :Fragment(), ProductsAdapter.PasstheData  {
    //1) Declaro variable del View Model
    lateinit var mViewModel: ProductsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {  // 2.0  se crea este método
        super.onCreate(savedInstanceState)
        //2.1) asigno el View Model a esta vista
        mViewModel= ViewModelProvider(this).get(ProductsViewModel::class.java)

    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mRecyclerView = idrecycler
        val madapter= ProductsAdapter(this)
        mRecyclerView.adapter=madapter
        mRecyclerView.setHasFixedSize(true) //para que tengan el mismo tamaño
        mRecyclerView.layoutManager= LinearLayoutManager(context) //si fueran una grilla sería GridLayout


        //3) observo la finción que retorna del LiveData desde ViewModel
        mViewModel.exposeLiveDataFromServer().observe(viewLifecycleOwner, Observer {
            // Log.d("View",it.toString())
            madapter.updateProducts(it)

        })


    }


    override fun passTheData(mProd: ProductsEntity) {
        val mBundle=Bundle()

        mBundle.putInt("id",mProd.id)   //put debe ser int o string según corresponda


        //llega el registro a editar
        Toast.makeText(context,mProd.name, Toast.LENGTH_LONG).show()

        //estoy pasando el objeto Bundle, de un fragmento a otro
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,mBundle)

    }
}