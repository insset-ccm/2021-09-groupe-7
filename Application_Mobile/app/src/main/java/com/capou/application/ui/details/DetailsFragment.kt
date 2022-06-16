package com.capou.application.ui.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.capou.application.R
import com.capou.application.api.Api
import com.capou.application.api.DetailsProduct
import com.capou.application.databinding.FragmentDetailsBinding
import com.capou.application.helper.FonctionHelper
import com.capou.application.ui.PickupPointFragment
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
        var tt = activity?.actionBar?.title
        Log.d("TAGS", "onCreateView: ${tt}")
        val root: View = binding.root

     /*   val detail: TextView = binding.detailProduct
        detailsViewModel.detail.observe(viewLifecycleOwner,{
            detail.text = it
        })*/


         Glide.with(this).load(this.imageName).into(binding.imageView)
        var newText = FonctionHelper().helpterText(this.customerName)
        binding.titleProduct.text = newText

       // Toast.makeText(context,this.customerName,Toast.LENGTH_LONG).show();
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }


    override fun onStart() {
        super.onStart()

        binding.Ajouter.setOnClickListener(View.OnClickListener {
            val fragment: Fragment = PickupPointFragment(this.customerName)
            val fragmentManager : FragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main, fragment).addToBackStack(
                DetailsFragment::class.java.name).commit()
        })

        Log.d("Informations:",this.customerName.toString())
        Api.getDetailsProducts().getDetails(this.customerName).enqueue(object : Callback<DetailsProduct?> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<DetailsProduct?>,
                response: Response<DetailsProduct?>
            ) {
                binding.calorie.text = "Calories: ${response.body()?.data?.aliments_info?.calorie.toString()}"
                binding.vitamies.text = "Vitamines: ${response.body()?.data?.aliments_info?.vitamines.toString()}"
                binding.protein.text = "Protéines: ${response.body()?.data?.aliments_info?.proteine.toString()}"
                binding.glucide.text = "Glucides: ${response.body()?.data?.aliments_info?.glucides.toString()}"
                binding.description.text = "Description: ${response.body()?.data?.description.toString()}"
                val test =  getVitamineList(response.body()?.data?.aliments_info?.vitamines)
                Log.d("Informations: ",test+" "+response.body().toString())
            }

            override fun onFailure(call: Call<DetailsProduct?>, t: Throwable) {
                Log.d("Error:",t.message.toString())
            }
        })

    }

    fun getVitamineList(listVitamines: ArrayList<String>?):String{
        var message = "";
        if (listVitamines != null) {
            for (list in listVitamines){
                Log.d("TAG", "getVitamineList: "+list)
                message = message + list
            }
        }
        Log.d("TAG", "getVitamineList: "+message)
        return message
    }

}
