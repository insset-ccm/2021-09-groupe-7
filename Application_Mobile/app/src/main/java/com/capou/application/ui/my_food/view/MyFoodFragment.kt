package com.capou.application.ui.my_food.view

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
import com.capou.application.databinding.MyFoodFragmentBinding
import com.capou.application.model.AlimentModel
import com.capou.application.model.AlimentPointVentes
import com.capou.application.ui.details.DetailsFragment
import com.capou.application.ui.maraicher.addProduct.view.AddProduct
import com.capou.application.ui.my_food.adapter.MyFoodAdapter
import com.capou.application.ui.my_food.viewModel.MyFoodViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MyFoodFragment : Fragment() {
    private lateinit var binding: MyFoodFragmentBinding
    private lateinit var viewModel: MyFoodViewModel
    private lateinit var adapter: MyFoodAdapter
    private var currentUser = Firebase.auth.currentUser?.uid.toString()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MyFoodFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {

        super.onStart()
        adapter = MyFoodAdapter{
                item,view -> onItemClick(item, view)
        }
        viewModel = ViewModelProvider(this).get(MyFoodViewModel::class.java)
        viewModel.getMyFoodList().observe( this,{
            adapter.submitList(it)
        })

        binding.recyclerFood.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.recyclerFood.adapter = adapter
    }


    private fun onItemClick (aliment: AlimentPointVentes, view: View){
        Log.d("Details: ","${aliment} ${view}")
        if(currentUser != null && aliment.name !=null)
        viewModel.deleteMyFood(aliment.name,currentUser)
    }
}