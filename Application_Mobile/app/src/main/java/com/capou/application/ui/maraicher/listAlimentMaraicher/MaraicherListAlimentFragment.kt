package com.capou.application.ui.maraicher.listAlimentMaraicher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.capou.application.databinding.FragmentListAlimentMaraicherBinding

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

        val textView: TextView = binding.textListAlimentMaraicher
        maraicherListAlimentViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}