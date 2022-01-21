package com.capou.application.ui.maraicher.listAlimentMaraicher

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.capou.application.R
import com.capou.application.databinding.FragmentListAlimentMaraicherBinding
import com.capou.application.model.AlimentModel
import com.capou.application.ui.details.DetailsFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MaraicherListAlimentFragment : Fragment() {


        private  lateinit var maraicherListAlimentViewModel: MaraicherListAlimentViewModel
        private var _binding: FragmentListAlimentMaraicherBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        maraicherListAlimentViewModel =
            ViewModelProvider(this).get(MaraicherListAlimentViewModel::class.java)

        _binding = FragmentListAlimentMaraicherBinding.inflate(inflater, container, false)
        val root: View = binding.root

       // val textView: TextView = binding.textListAlimentMaraicher

        binding.registerBtn.setOnClickListener {

            val fr_name = binding.frName.text.toString()
            val nom = binding.nom.text.toString()

            maraicherListAlimentViewModel.saveAliment(fr_name, nom)

            binding.frName.text.clear()
            binding.nom.text.clear()
        }



        return root

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}