package com.example.work.jinaryafirebase.People

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class PeoplePageAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {



    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                SuggestedFragment()
            } 1 -> {
                FollowingFragment()
            }
            else -> {
                return FollowersFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Suggested"
            1 -> "Following"
            else -> {
                return "Followers"
            }
        }
    }
}