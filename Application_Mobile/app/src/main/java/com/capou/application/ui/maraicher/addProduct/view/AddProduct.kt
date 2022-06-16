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
import com.capou.application.databinding.FragmentAddProductBinding
import com.capou.application.helper.FonctionHelper
import com.capou.application.ui.maraicher.addProduct.viewModel.AddProductViewModel

class AddProduct : Fragment {

    private lateinit var _binding: FragmentAddProductBinding
    private lateinit var viewModel: AddProductViewModel
    private var pickup :String = "11 Rue Voltaire, Saint-Quentin"
    val customerName: String
    val imageName:String
    private lateinit var pick:String
    private var list:MutableList<String?> = mutableListOf("Insset")

    constructor(customerName: String, imageName:String) : super() {
        this.customerName = customerName
        this.imageName = imageName
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddProductViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        viewModel.getListPick().observe(this,{
            Log.d("TAG", "onStart: ${it}")
            this.list.addAll(it)

            val array = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, this.list)
            array.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            _binding.pickupPoint.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    val selectedItem = parent.getItemAtPosition(position).toString()
                    pick = selectedItem
                } // to close the onItemSelected

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
            _binding.pickupPoint.adapter = array

            _binding.addProduct.setOnClickListener {
                viewModel.addProductMarai(this.customerName,pick)
                //  Toast.makeText(this.context, "Produit Ajouté",Toast.LENGTH_LONG).show()
            }

        })

        //this.list = listOf("3 Rue Litré, Lille","5 Rue Voltaire, Saint-Quentin","24 Rue Alexandre Dumas, Nantes","7 boulevard Bryas, Creil","81 rue Porte d'Orange, Cenon"," 5 Chemin Des Bateliers, Angers"," 33 rue Banaudon, Lyon")


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentAddProductBinding.inflate(layoutInflater,container,false)
        val title = FonctionHelper().helpterText(this.customerName)
        _binding.title.text = title

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



        return _binding.root
    }


}