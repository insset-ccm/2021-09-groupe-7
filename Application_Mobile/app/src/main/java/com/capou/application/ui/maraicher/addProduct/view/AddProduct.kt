package com.capou.application.ui.maraicher.addProduct.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.capou.application.R
import com.capou.application.databinding.FragmentAddProductBinding
import com.capou.application.ui.authentification.FirebaseAuthViewModel
import com.capou.application.ui.maraicher.addProduct.ViewModel.AddProductViewModel

class AddProduct : Fragment {

    private lateinit var _binding: FragmentAddProductBinding
    private lateinit var viewModel: AddProductViewModel
    private var pickup :String = "11 Rue Voltaire, Saint-Quentin"
    val customerName: String
    val imageName:String

    constructor(customerName: String, imageName:String) : super() {
        this.customerName = customerName
        this.imageName = imageName
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddProductViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("Debug","${this.customerName}")
        _binding = FragmentAddProductBinding.inflate(layoutInflater,container,false)
        _binding.title.text = this.customerName
        Glide.with(this).load(this.imageName).into(_binding.images)
        _binding.pickupPoint.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                Log.d("Debug","${selectedItem}")
                pickup = selectedItem.toString()
            } // to close the onItemSelected

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
        val list = arrayOf("Default","Other","Bonjour")
        val array = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, list)
        array.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        _binding.pickupPoint.adapter = array

        _binding.addProduct.setOnClickListener {
            viewModel.addProduct(this.customerName)
        }
        return _binding.root
    }


}