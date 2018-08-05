package com.example.work.jinaryafirebase.Home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.work.jinaryafirebase.R


class HomeMyPageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val homeFragmentView = inflater.inflate(
                R.layout.home_my_page_fragment, container,
                false
        )

        return homeFragmentView
    }

}
