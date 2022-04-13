package com.capou.application.ui.viewpager.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.capou.application.ui.my_food.view.MyFoodFragment
import com.capou.application.ui.pickuppoint.UserInfoFragment

class ViewPagerAdapter(fm: Fragment) : FragmentStateAdapter(fm) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment =  MyFoodFragment()
        if(position == 1){
            fragment = UserInfoFragment()
        }
        return fragment
    }
}