package com.capou.application.ui.details

import android.content.Intent
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
import com.capou.application.Data
import com.capou.application.api.Api
import com.capou.application.api.DetailsProduct
import com.capou.application.api.Nutrition
import com.capou.application.comments.Comments
import com.capou.application.databinding.FragmentDetailsBinding
import com.capou.application.ui.pickup_point.view.PickUpPoint
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailsFragment : Fragment {
    val customerName: String
    val imageName:String

    constructor(customerName: String, imageName:String) : super() {
        this.customerName = customerName
        this.imageName = imageName
    }

    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var _binding: FragmentDetailsBinding

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

     /*   val detail: TextView = binding.detailProduct
        detailsViewModel.detail.observe(viewLifecycleOwner,{
            detail.text = it
        })*/


         Glide.with(this).load(this.imageName).into(binding.imageView)
        binding.titleProduct.text = this.customerName

        Toast.makeText(context,this.customerName,Toast.LENGTH_LONG).show();
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }


    override fun onStart() {
        super.onStart()

        binding.Ajouter.setOnClickListener(View.OnClickListener {
           // val intent = Intent(context,Comments::class.java)
            val intent = Intent(context,PickUpPoint::class.java)
            // change the value of the title
            intent.putExtra("title",this.customerName)
            startActivity(intent)
        })

        Log.d("Informations:",this.customerName.toString())
        Api.getDetailsProducts().getDetails(this.customerName).enqueue(object : Callback<DetailsProduct?> {
            override fun onResponse(
                call: Call<DetailsProduct?>,
                response: Response<DetailsProduct?>
            ) {
                var message = "${response.body()?.family.toString()} \n ${response.body()?.nutritions?.calories.toString()} \n ${response.body()?.nutritions?.fat.toString()}"

                    //response.body().toString()
                binding.detailCarbohydrates.text ="Carbohydrates: ${response.body()?.nutritions?.carbohydrates.toString()}"
                binding.detailCalories.text = "Calories: ${response.body()?.nutritions?.calories.toString()}"
                binding.detailFat.text = "Matières Grasses: ${response.body()?.nutritions?.fat.toString()}"
                binding.detailProtein.text = "Protéines: ${response.body()?.nutritions?.protein.toString()}"
                binding.detailSugar.text = "Sucres: ${response.body()?.nutritions?.sugar.toString()}"
                Log.d("Informations: ",response.body().toString())
            }

            override fun onFailure(call: Call<DetailsProduct?>, t: Throwable) {
                Log.d("Error:",t.message.toString())
            }
        })

    }

}
