package com.capou.application.ui.pickup_point.view

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capou.application.R
import com.capou.application.comments.ChuckNorrisAdapter
import com.capou.application.databinding.PickUpPointFragmentBinding
import com.capou.application.model.CommentModel
import com.capou.application.model.PickUpPoint
import com.capou.application.ui.aliments.fragments.HomeFragmentAliment
import com.capou.application.ui.details.DetailsFragment
import com.capou.application.ui.home.HomeFragment
import com.capou.application.ui.pickup_point.adapter.PickUpPointAdapter
import com.capou.application.ui.pickup_point.viewModel.PickUpPointViewModel

class PickUpPoint : AppCompatActivity() {

    private lateinit var binding: PickUpPointFragmentBinding
    private lateinit var viewModel:PickUpPointViewModel
    private lateinit var adapter:PickUpPointAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PickUpPointFragmentBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(PickUpPointViewModel::class.java)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        val getTitle:String = intent.getStringExtra("title").toString()
        Log.d("Details","${getTitle}")
     /*   viewModel.getPickUpPoint(this,getTitle).observe(this,{
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

        binding.recyclerPickupPoint.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerPickupPoint.adapter = adapter*/

    }

    private fun onItemClick(pickUpPoint: PickUpPoint, view : View) {
        // view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        Log.d("Debugger","${pickUpPoint.listMaraicher}")
        //Toast.makeText(this,"${pickUpPoint}", Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun createDialog(){
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Information")
        alert.setMessage("Ce produit n'a pas de point relais")
        alert.setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
            this.onBackPressed()
        })
        alert.show()
    }
}
