package com.example.work.jinaryafirebase.Insights

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.work.jinaryafirebase.Compliments.ComplimentsReceivedFragment
import com.example.work.jinaryafirebase.Compliments.ComplimentsSentFragment

class InsightsPageAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                InsightsReceivedFragment()
            }
            else -> {
                return MyInsightsFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Connections"
            else -> {
                return "My Insights"
            }
        }
    }
}