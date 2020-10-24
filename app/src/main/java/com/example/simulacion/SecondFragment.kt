package com.example.simulacion

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.simulacion.model.viewModel.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    lateinit var mViewModel: ProductsViewModel
    var idProducts: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel =
            ViewModelProvider(this).get(ProductsViewModel::class.java) //variable representa VM
        arguments?.let {
            idProducts= it.getInt("id")   //se pasa la key de la clase
            Log.d("LET",idtextView1.toString())
        }
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idProducts?.let {
            Log.d("ingresé al LET","")
            //la función debe devolver LiveData
            mViewModel.getOneFrutoskByID(it).observe(viewLifecycleOwner, Observer{// getOneFrutoskByID método que debo crear y luego hacerlo en el viewmodel

                Log.d("OBJ_LIVE",it.toString())

                Glide.with(this).load(it.image).into(idmageView)
                idtextView1.setText(it.name)

                idtextView2.setText(it.price)

                Log.d("seg",it.name)
            })

        }

        button_second.setOnClickListener{
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

    }


}