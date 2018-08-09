package com.example.work.jinaryafirebase.Person

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.work.jinaryafirebase.R

class PersonInsights : AppCompatActivity() {

    var personName = "Person Name"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.person_insights_content)

        title = personName + getString(R.string.insights)

    }

}
