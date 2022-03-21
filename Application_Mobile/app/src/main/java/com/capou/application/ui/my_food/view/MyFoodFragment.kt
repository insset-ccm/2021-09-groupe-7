package com.capou.application.ui.my_food.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capou.application.databinding.MyFoodFragmentBinding
import com.capou.application.ui.my_food.adapter.MyFoodAdapter
import com.capou.application.ui.my_food.viewModel.MyFoodViewModel
import com.capou.application.ui.pickup_point.adapter.PickUpPointAdapter

class MyFoodFragment : Fragment() {
    private lateinit var binding: MyFoodFragmentBinding
    private lateinit var viewModel: MyFoodViewModel
    private lateinit var adapter: MyFoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MyFoodFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        adapter = MyFoodAdapter()
        viewModel = ViewModelProvider(this).get(MyFoodViewModel::class.java)
        viewModel.getMyFoodList().observe( this,{
            Log.d("Debug","${it}")
            adapter.submitList(it)
        })

        binding.recyclerFood.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.recyclerFood.adapter = adapter
    }

}