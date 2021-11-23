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
    val data = Firebase.database.getReference("aliments")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailsViewModel =
            ViewModelProvider(this).get(DetailsViewModel::class.java)

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

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

        data.child("0").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                Log.d("Bon",snapshot.toString())

                    snapshot.children.map { dataSnapshot ->
                        Log.d("Detailts",dataSnapshot.key.toString()+" -- "+dataSnapshot.getValue().toString())
                        when(dataSnapshot.key.toString().trim()){
                            "images" -> { Glide.with(this@DetailsFragment).load(dataSnapshot.value.toString()).into(binding.imageView)}
                            "nom" -> { binding.titleProduct.text = dataSnapshot.value.toString().uppercase()}
                            "saison" -> {
                                var message: String = "";
                                for (season in dataSnapshot.children){
                                    message = message +" - "+season.value.toString()

                                }
                                binding.detailProduct.text = message

                            }
                            else -> { Log.d("Det","Error") }
                        }
                    }

            }

            override fun onCancelled(error: DatabaseError) {

                Log.d("Bon", error.message)
            }

        })

    }

}
