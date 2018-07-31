package com.example.work.jinaryafirebase.People

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.work.jinaryafirebase.R

class SuggestedFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val suggestedFragmentView = inflater.inflate(
                R.layout.fragment_suggested, container,
                false
        )

        return suggestedFragmentView
    }
}