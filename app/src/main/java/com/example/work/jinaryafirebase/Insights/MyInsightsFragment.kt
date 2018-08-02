package com.example.work.jinaryafirebase.Insights

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.work.jinaryafirebase.R

class MyInsightsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val insightsSentFragmentView = inflater.inflate(
                R.layout.fragment_my_insights, container,
                false
        )

        return insightsSentFragmentView
    }

}