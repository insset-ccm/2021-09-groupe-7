package com.capou.application.ui.aliments.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capou.application.R
import com.capou.application.comments.AlimentAdapter
import com.capou.application.databinding.FragmentHomeAlimentBinding
import com.capou.application.model.AlimentModel
import com.capou.application.ui.aliments.repository.AlimentRepository
import com.capou.application.ui.details.DetailsFragment
import com.capou.application.ui.maraicher.addProduct.viewModel.AddProductViewModel
import com.capou.application.ui.maraicher.addProduct.view.AddProduct


class HomeFragmentAliment(): Fragment() {
    private lateinit var  binding: FragmentHomeAlimentBinding
    private val repository: AlimentRepository by lazy { AlimentRepository()}
    private lateinit var viewModel : AddProductViewModel

    private lateinit var adapterAliment :AlimentAdapter
    private var getType:String? = "utilisateur"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeAlimentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(AddProductViewModel::class.java)
        val root: View = binding.root

        //créer une liste qui va stocker les aliments
            return root
        }

    override fun onStart() {
        super.onStart()

        viewModel.getUserInfo().observe(this,{
            Log.d("Debug","${it}")
            if(!it.isNullOrEmpty()){
                getType = it.toString()
                Log.d("Debug","${getType} 1 ")
            }
        })



        repository.default.observe(this,{
            adapterAliment.submitList(it)
        })
        adapterAliment = AlimentAdapter{
                item,view -> onItemClick(item, view)
        }
        binding.verticalRecyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.verticalRecyclerView.adapter = adapterAliment

    }

    private fun onItemClick (aliment: AlimentModel, view: View){
        Log.d("Details: ",aliment.nom)
        Log.d("Debug","${getType} ")

           if(getType?.lowercase() == "utilisateur"){
                val fragment: Fragment = DetailsFragment(aliment.nom,aliment.images);
                val fragmentManager : FragmentManager = parentFragmentManager
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main, fragment).addToBackStack(
                    DetailsFragment::class.java.name).commit()
            }
            else{
                val fragment: Fragment = AddProduct(aliment.nom,aliment.images);
                val fragmentManager : FragmentManager = parentFragmentManager
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main, fragment).addToBackStack(
                    AddProduct::class.java.name).commit()
            }


    }

}
