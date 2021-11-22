package com.capou.application.ui.aliments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.capou.application.R
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.capou.application.databinding.FragmentHomeAlimentBinding
import com.capou.application.ui.aliments.adapter.AlimentAdapter
import com.capou.application.ui.aliments.modal.AlimentModal


class HomeFragmentAliment(): Fragment() {
    private lateinit var  binding: FragmentHomeAlimentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeAlimentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //créer une liste qui va stocker les aliments
        val alimentList = arrayListOf<AlimentModal>()

        // enregistrer un premièr aliment
        alimentList.add(AlimentModal(
            "mangue",
            "https://cdn.pixabay.com/photo/2017/01/15/18/23/mango-1982330_1280.jpg",
            "hiver"
        ))
            binding.verticalRecyclerView.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            binding.verticalRecyclerView.adapter =
                AlimentAdapter(alimentList, R.layout.item_vertical_aliment)

            return root
        }

}
