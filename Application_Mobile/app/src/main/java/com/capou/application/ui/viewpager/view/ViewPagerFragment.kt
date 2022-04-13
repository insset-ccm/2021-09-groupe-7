package com.capou.application.ui.viewpager.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.capou.application.databinding.ViewPagerFragmentBinding
import com.capou.application.ui.viewpager.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerFragment : Fragment() {

   /* companion object {
        fun newInstance() = ViewPagerFragment()
    }

    private lateinit var viewModel: ViewPagerViewModel*/
    private lateinit var demoCollectionAdapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tableLayout: TabLayout
    private lateinit var binding: ViewPagerFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ViewPagerFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        demoCollectionAdapter = ViewPagerAdapter(this)
        tableLayout = binding.tablayout
        viewPager = binding.viewpager
        viewPager.adapter = demoCollectionAdapter

        TabLayoutMediator(tableLayout, viewPager){
            tab,index ->
            tab.text = when(index){
                0 -> {"Aliments"}
                else ->{"Point de Ventes"}
            }
        }.attach()
    }
}