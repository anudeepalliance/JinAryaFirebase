package com.example.work.jinaryafirebase.Home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.work.jinaryafirebase.R


class NotificationsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val notificationsFragmentView = inflater.inflate(
                R.layout.notifications_fragment, container,
                false
        )

        return notificationsFragmentView
    }

}
