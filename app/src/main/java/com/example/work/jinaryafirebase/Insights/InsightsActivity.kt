package com.example.work.jinaryafirebase.Insights

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.work.jinaryafirebase.AddInsight.InsightAdd
import com.example.work.jinaryafirebase.People.PeopleActivity
import com.example.work.jinaryafirebase.R
import kotlinx.android.synthetic.main.insights_app_bar_main.*
import kotlinx.android.synthetic.main.insights_my_list_content.*
import org.jetbrains.anko.startActivity

class InsightsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.insights_drawer)

        setTitle(R.string.insights)

        setSupportActionBar(insights_toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val insightsFragmentAdapter =
                InsightsPageAdapter(supportFragmentManager)

        insights_viewpager.adapter = insightsFragmentAdapter

        insights_tabs.setupWithViewPager(insights_viewpager)

        add_insight_fab.setOnClickListener {
            startActivity<InsightAdd>()
        }
    }
}
