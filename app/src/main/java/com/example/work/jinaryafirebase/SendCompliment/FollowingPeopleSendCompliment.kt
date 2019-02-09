package com.example.work.jinaryafirebase.SendCompliment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.work.jinaryafirebase.R
import kotlinx.android.synthetic.main.following_people_send_compliment_app_bar_main.*

class FollowingPeopleSendCompliment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.following_people_send_compliment_drawer)

        //TODO dynamically set the title based on the Intent Received
        setTitle(R.string.following)

        setSupportActionBar(following_people_toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}
