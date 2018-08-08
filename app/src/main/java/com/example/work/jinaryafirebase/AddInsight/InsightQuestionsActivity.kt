package com.example.work.jinaryafirebase.AddInsight

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.work.jinaryafirebase.R
import kotlinx.android.synthetic.main.insight_questions_app_bar_main.*

class InsightQuestionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.insight_questions_drawer)

        setTitle(R.string.insight_questions)

        setSupportActionBar(add_insight_toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}
