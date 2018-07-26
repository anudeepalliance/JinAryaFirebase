package com.example.work.jinaryafirebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class SearchPeopleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_people)

        setTitle(R.string.search_people)
    }
}
