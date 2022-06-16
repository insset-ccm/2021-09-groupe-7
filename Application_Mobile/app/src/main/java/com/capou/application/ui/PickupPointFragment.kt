package com.capou.application.ui

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capou.application.databinding.FragmentPickuPointBinding
import com.capou.application.model.PickUpPoint
import com.capou.application.ui.maraicher.addProduct.viewModel.AddProductViewModel
import com.capou.application.ui.pickup_point.adapter.PickUpPointAdapter
import com.capou.application.ui.pickup_point.viewModel.PickUpPointViewModel

class PickupPointFragment(val productName: String) : Fragment()  {


    private lateinit var binding: FragmentPickuPointBinding
    private lateinit var viewModel: PickUpPointViewModel
    private lateinit var viewModel2: AddProductViewModel
    private lateinit var adapter: PickUpPointAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPickuPointBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(PickUpPointViewModel::class.java)
        viewModel2 = ViewModelProvider(this).get(AddProductViewModel::class.java)
        val root: View = binding.root
        return root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getPickUpPoint(requireContext(),this.productName).observe(this,{
            Log.d("Details"," "+it)
            adapter.submitList(it)
            Log.d("deb","${it.isEmpty()}")
            if(it.isEmpty()){
                createDialog()
            }

        })
        adapter = PickUpPointAdapter{
                item,view -> onItemClick(item, view)
        }

        binding.pickup.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.pickup.adapter = adapter

    }

    private fun onItemClick(pickUpPoint: PickUpPoint, view : View) {
        // view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        Log.d("Debugger","${pickUpPoint}")
        viewModel2.addProduct(this.productName, pickUpPoint.address.toString())
        //Toast.makeText(this,"${pickUpPoint}", Toast.LENGTH_LONG).show()
    }



    private fun createDialog(){
        val alert = AlertDialog.Builder(requireContext())
        alert.setTitle("Information")
        alert.setMessage("Ce produit n'a pas de point relais")
        alert.setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()

        })
        alert.show()
    }


}