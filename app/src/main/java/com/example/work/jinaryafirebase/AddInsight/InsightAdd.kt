package com.example.work.jinaryafirebase.AddInsight

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.work.jinaryafirebase.R
import kotlinx.android.synthetic.main.insight_add_app_bar_main.*

class InsightAdd : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.insight_add_drawer)

        setTitle(R.string.add_an_insight)

        setSupportActionBar(insight_add_toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}
