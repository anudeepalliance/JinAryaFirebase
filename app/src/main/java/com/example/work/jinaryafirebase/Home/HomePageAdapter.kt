package com.example.work.jinaryafirebase.Home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.work.jinaryafirebase.Insights.InsightsReceivedFragment
import com.example.work.jinaryafirebase.Insights.MyInsightsFragment

class HomePageAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                HomeFragment()
            }
            else -> {
                return NotificationsFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "My Page"
            else -> {
                return "Notifications"
            }
        }
    }
}