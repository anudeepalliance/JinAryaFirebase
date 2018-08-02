package com.example.work.jinaryafirebase.Compliments

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ComplimentsPageAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                ComplimentsReceivedFragment()
            }
            else -> {
                return ComplimentsSentFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Received"
            else -> {
                return "Sent"
            }
        }
    }
}