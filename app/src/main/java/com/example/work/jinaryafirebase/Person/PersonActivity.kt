package com.example.work.jinaryafirebase.Person

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.work.jinaryafirebase.R
import kotlinx.android.synthetic.main.person_page.*

class PersonActivity : AppCompatActivity() {

    var personName = "Person Name"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.person_page)

        title = personName

        personComplimentsIntent()

        personInsightsIntent()

        personFollowersIntent()

        personFollowingIntent()

    }

    private fun personComplimentsIntent() {
        person_compliments.setOnClickListener {
            startActivity(Intent(this, PersonComplimentsReceived::class.java))
        }
    }

    private fun personInsightsIntent() {
        person_insights.setOnClickListener {
            startActivity(Intent(this, PersonInsights::class.java))
        }
    }

    private fun personFollowersIntent() {
        person_followers.setOnClickListener {
            startActivity(Intent(this, PersonFollowers::class.java))
        }
    }

    private fun personFollowingIntent() {

        person_following.setOnClickListener {
            startActivity(Intent(this, PersonFollowing::class.java))
        }
    }


}
