package com.example.work.jinaryafirebase.Home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.work.jinaryafirebase.R


class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val homeFragmentView = inflater.inflate(
                R.layout.fragment_my_page, container,
                false
        )

        return homeFragmentView
    }

}
