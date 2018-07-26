package com.example.work.jinaryafirebase.People

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class PeoplePageAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {



    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                SuggestedFragment()
            }
            else -> {
                return FollowingFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Suggested"
            else -> {
                return "Following"
            }
        }
    }
}