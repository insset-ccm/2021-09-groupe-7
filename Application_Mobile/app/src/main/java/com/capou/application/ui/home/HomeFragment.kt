package com.capou.application.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.capou.application.R
import com.capou.application.databinding.FragmentHomeBinding
import com.capou.application.ui.details.DetailsFragment

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

      /*  supportFragmentManager.commit {
            replace<ExampleFragment>(R.id.fragment_container)
            setReorderingAllowed(true)
            addToBackStack(null)
        }*/

      /*  binding.starDetails.setOnClickListener {
           val fragment:Fragment = DetailsFragment("tomate","defautl");


            //getFragmentManager

            /* supportFragmentManager.commit {
                replace<ExampleFragment>(R.id.fragment_container)
                setReorderingAllowed(true)
                addToBackStack(null)
            }*/
            val fragmentManager : FragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main, fragment).commit()

        }*/
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}