package com.example.work.jinaryafirebase.Compliments

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.work.jinaryafirebase.R
import kotlinx.android.synthetic.main.compliments.*

class ComplimentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.compliments)

        setTitle(R.string.compliments)

        val complimentsFragmentAdapter =
                ComplimentsPageAdapter(supportFragmentManager)

        compliments_viewpager.adapter = complimentsFragmentAdapter

        compliments_tabs.setupWithViewPager(compliments_viewpager)
    }

}
