package com.example.work.jinaryafirebase.Compliments

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.work.jinaryafirebase.CompanionObjects.Companion.intentToFollowingPeopleActivity
import com.example.work.jinaryafirebase.CompanionObjects.Companion.intentToSendComplimentActivity
import com.example.work.jinaryafirebase.R
import kotlinx.android.synthetic.main.compliments_app_bar_main.*
import kotlinx.android.synthetic.main.compliments_content.*
import kotlinx.android.synthetic.main.home_app_bar_main.*

class ComplimentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.compliments_drawer)

        setTitle(R.string.compliments)

        setSupportActionBar(compliments_toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val complimentsFragmentAdapter =
                ComplimentsPageAdapter(supportFragmentManager)

        compliments_viewpager.adapter = complimentsFragmentAdapter

        compliments_tabs.setupWithViewPager(compliments_viewpager)

        setUpComplimentsFab()
    }

    private fun setUpComplimentsFab() {
        add_compliment_fab.setOnClickListener {
            startActivity(intentToFollowingPeopleActivity(this))
        }
    }

}
