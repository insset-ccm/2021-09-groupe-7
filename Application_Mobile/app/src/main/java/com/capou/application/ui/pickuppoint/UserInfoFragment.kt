package com.capou.application.ui.pickuppoint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.capou.application.databinding.UserInfoFragmentBinding
import com.capou.application.ui.pickuppoint.adapter.ExpandableListAdapter
import com.capou.application.ui.pickuppoint.viewModel.PickUpViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class UserInfoFragment : Fragment() {
    private lateinit var  binding: UserInfoFragmentBinding
    private lateinit var expandableListView : ExpandableListView
    private lateinit var viewModel: PickUpViewModel

    private var titleList: List<String?>? = null
    private  var list: HashMap<String?, List<String?>> ? = null
    private val auth = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PickUpViewModel::class.java)
        binding = UserInfoFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }




    override fun onStart() {
        super.onStart()
        viewModel.getListPickUpPoint().observe(this, {
            titleList = ArrayList(it.keys)
            list = it
            expandableListView = binding.expandableListView
            val expandableAdapter = ExpandableListAdapter(titleList, list )
            expandableListView.setAdapter( expandableAdapter)
            expandableListView.setOnGroupExpandListener(ExpandableListView.OnGroupExpandListener {
            })
        })


    }


}
