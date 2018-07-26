package com.example.work.jinaryafirebase

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.work.jinaryafirebase.People.PeopleActivity
import kotlinx.android.synthetic.main.activity_create_profile.*

class CreateProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)

        setTitle(R.string.create_profile)

        save_profile.setOnClickListener {
            val intent = Intent(this, PeopleActivity::class.java)
            startActivity(intent)
        }
    }
}
