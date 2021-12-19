package com.capou.application.ui.maraicher.homeMaraicher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.capou.application.databinding.FragmentHomeMaraicherBinding

class MaraicherHomeFragment : Fragment(){
    private lateinit var maraicherHomeViewModel: MaraicherHomeViewModel
    private var _binding: FragmentHomeMaraicherBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        maraicherHomeViewModel =
            ViewModelProvider(this).get(MaraicherHomeViewModel::class.java)

        _binding = FragmentHomeMaraicherBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHomeMaraicher
        maraicherHomeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}