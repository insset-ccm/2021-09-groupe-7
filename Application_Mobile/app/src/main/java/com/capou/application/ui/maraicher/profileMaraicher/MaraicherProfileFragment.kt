package com.capou.application.ui.maraicher.profileMaraicher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.capou.application.databinding.FragmentMaraicherProfileBinding

class MaraicherProfileFragment : Fragment() {

    private lateinit var maraicherProfileViewModel: MaraicherProfileViewModel
    private var _binding: FragmentMaraicherProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        maraicherProfileViewModel =
            ViewModelProvider(this).get(MaraicherProfileViewModel::class.java)

        _binding = FragmentMaraicherProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textMaraicherProfile
        maraicherProfileViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}