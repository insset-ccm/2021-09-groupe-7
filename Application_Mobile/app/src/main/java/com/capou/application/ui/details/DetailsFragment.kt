package com.capou.application.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.capou.application.api.DetailsProduct
import com.capou.application.api.DetailsRoom
import com.capou.application.databinding.FragmentDetailsBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class DetailsFragment : Fragment {

    val customerName: String


    constructor(customerName: String) : super() {
        this.customerName = customerName
    }

    private lateinit var detailsViewModel: DetailsViewModel
    private var _binding: FragmentDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val data = Firebase.database.reference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailsViewModel =
            ViewModelProvider(this).get(DetailsViewModel::class.java)

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*context?.let {
            Glide.with(it)
                .load("https://cdn.pixabay.com/photo/2016/03/26/16/44/tomatoes-1280859_960_720.jpg")
                .into(binding.imageView)
        }*/
      //  binding.titleProduct.text = this.customerName;

        val detail: TextView = binding.detailProduct
        detailsViewModel.detail.observe(viewLifecycleOwner,{
            detail.text = it
        })


        Toast.makeText(context,this.customerName,Toast.LENGTH_LONG).show();
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onStart() {
        super.onStart()
        //val data = Firebase.database.reference
        Log.d("Bonjour",data.toString())
        //val issuccess = data.setValue("Bonjour").isSuccessful
        // Log.d("Bon",issuccess.toString())
      /*  data.setValue("H -")
            .addOnSuccessListener {
                Log.d("Bonj","yes")

            }
            .addOnFailureListener { ex : Exception ->
                Log.d("TAG", ex.toString())

            }
    */
        data.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                Log.d("Bon",snapshot.toString())
            }

            override fun onCancelled(error: DatabaseError) {

                Log.d("Bon", error.message)
            }

        })

        data.child("aliments").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
               val list =  snapshot.getValue(DetailsProduct::class.java)
                Log.d("Bonj",list.toString())
            //  binding.titleProduct.text = list?.nom.toString()
               /* Glide.with(context!!)
                    .load(list?.images.toString())
                    .into(binding.imageView)*/
             /*  snapshot.children.map {

                       Glide.with(context!!)
                           .load(it.value.toString())
                           .into(binding.imageView)

               }*/
            }

            override fun onCancelled(error: DatabaseError) {

                Log.d("Bon", error.message)
            }

        })
    }

}
