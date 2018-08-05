package com.example.work.jinaryafirebase.Insights

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.work.jinaryafirebase.R
import kotlinx.android.synthetic.main.insights_my_list.*

class InsightsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.insights_my_list)

        setTitle(R.string.insights)

        val insightsFragmentAdapter =
                InsightsPageAdapter(supportFragmentManager)

        insights_viewpager.adapter = insightsFragmentAdapter

        insights_tabs.setupWithViewPager(insights_viewpager)
    }
}
