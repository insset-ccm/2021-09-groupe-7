package com.capou.application.ui.aliments.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capou.application.R
import com.capou.application.comments.AlimentAdapter
import com.capou.application.comments.ChuckNorrisAdapter
import com.capou.application.databinding.FragmentHomeAlimentBinding
import com.capou.application.model.AlimentModel
import com.capou.application.ui.aliments.repository.AlimentRepository
import com.capou.application.ui.details.DetailsFragment


class HomeFragmentAliment(): Fragment() {
    private lateinit var  binding: FragmentHomeAlimentBinding
    private val repository: AlimentRepository by lazy { AlimentRepository() }
    private lateinit var adapterAliment :AlimentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeAlimentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //créer une liste qui va stocker les aliments


            return root
        }

    override fun onStart() {
        super.onStart()

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
        val fragment:Fragment = DetailsFragment(aliment.nom,aliment.images);
        val fragmentManager : FragmentManager = parentFragmentManager
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main, fragment).commit()
    }

}
