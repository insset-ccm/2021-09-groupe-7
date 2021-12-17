package com.capou.application.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capou.application.R
import com.capou.application.comments.AlimentAdapter
import com.capou.application.databinding.FragmentDashboardBinding
import com.capou.application.model.AlimentModel
import com.capou.application.ui.aliments.repository.AlimentRepository
import com.capou.application.ui.details.DetailsFragment

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null
    private val repository: AlimentRepository by lazy { AlimentRepository() }
    private lateinit var adapterAliment :AlimentAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root


       /* val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onStart() {
        super.onStart()

        repository.default.observe(this,{
            adapterAliment.submitList(it)
        })
        adapterAliment = AlimentAdapter{
                item,view -> onItemClick(item, view)
        }
        binding.verticalRecyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.verticalRecyclerView.adapter = adapterAliment

    }

    private fun onItemClick (aliment: AlimentModel, view: View){
        Log.d("Details: ",aliment.nom)
        val fragment:Fragment = DetailsFragment(aliment.nom,aliment.images);
        val fragmentManager : FragmentManager = parentFragmentManager
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main, fragment).commit()
    }

}