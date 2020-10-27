package com.example.simulacion

import android.content.Intent
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
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

            idProducts= it.getInt("id")

            Log.d("LET",idProducts.toString())
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
       /* idProducts?.let {
            Log.d("ingresé al LET","")*/
            //la función debe devolver LiveData
            mViewModel.getOneProductskByID(idProducts!!).observe(viewLifecycleOwner, Observer {
                if (it != null) {

                    Log.d("OBJ_LIVE", it.toString())

                    Glide.with(this).load(it.image).into(idmageView)
                               /*  idtextView1.setText(it.name)

                    idtextView2.setText(it.price)*/

                    idtextView1.text=it.name
                    idtextView2.text=it.price.toString()

                    Log.d("seg", it.name)
                }
            })

      //  }

        button_second.setOnClickListener{
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

 fab.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {

         val intent = Intent(Intent.ACTION_SEND)
         val to = "info@plaplix.cl"
         val addressees = arrayOf(to)
         val subject = "Hola"
         val message = "Hola \nVi este super Heroe llamado y quiero comprarlo llámame al ___"
         intent.putExtra(Intent.EXTRA_EMAIL, addressees)
         intent.putExtra(Intent.EXTRA_SUBJECT, subject)
         intent.putExtra(Intent.EXTRA_TEXT, message)
         intent.type = "text/plain"
         startActivity(Intent.createChooser(intent, "Contactar Area de Ventas:"))



 }

    }
}