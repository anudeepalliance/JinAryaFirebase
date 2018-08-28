package com.example.work.jinaryafirebase.Home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.work.jinaryafirebase.CompanionObjects
import com.example.work.jinaryafirebase.CompanionObjects.Companion.profileImagesFolderRef

import com.example.work.jinaryafirebase.R
import kotlinx.android.synthetic.main.home_my_page_fragment.view.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import java.io.File


class HomeMyPageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val homeFragmentView : View = inflater.inflate(
                R.layout.home_my_page_fragment, container,
                false
        )


        populateUserPhoto(homeFragmentView)

        return homeFragmentView
    }

    private fun populateUserPhoto(view: View) {


        CompanionObjects.profileImagesFolderRef.downloadUrl.addOnSuccessListener {

            var downloadUrl = it.toString()

            Glide.with(this@HomeMyPageFragment)
                    .load(downloadUrl)
                    .into(view.my_page_image)

        }.addOnFailureListener {

        }

    }

}
