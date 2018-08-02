package com.example.work.jinaryafirebase.Insights

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.work.jinaryafirebase.Compliments.ComplimentsPageAdapter
import com.example.work.jinaryafirebase.R
import kotlinx.android.synthetic.main.activity_compliments.*
import kotlinx.android.synthetic.main.activity_insights.*

class InsightsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insights)

        setTitle(R.string.insights)

        val insightsFragmentAdapter =
                InsightsPageAdapter(supportFragmentManager)

        insights_viewpager.adapter = insightsFragmentAdapter

        insights_tabs.setupWithViewPager(insights_viewpager)
    }
}
