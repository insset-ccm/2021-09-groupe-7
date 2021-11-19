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
import com.capou.application.api.Api
import com.capou.application.api.DetailsRoom
import com.capou.application.databinding.FragmentDetailsBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call


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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailsViewModel =
            ViewModelProvider(this).get(DetailsViewModel::class.java)

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        context?.let {
            Glide.with(it)
                .load("https://cdn.pixabay.com/photo/2016/03/26/16/44/tomatoes-1280859_960_720.jpg")
                .into(binding.imageView)
        }
        binding.titleProduct.text = this.customerName;

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

}
