package com.example.work.jinaryafirebase.Compliments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.work.jinaryafirebase.R

class ComplimentsReceivedFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val receivedFragmentView = inflater.inflate(
                R.layout.compliments_received_fragment, container,
                false
        )

        return receivedFragmentView
    }

}