package com.example.work.jinaryafirebase

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.work.jinaryafirebase.AddInsight.InsightAdd
import com.example.work.jinaryafirebase.Home.HomeActivity
import kotlinx.android.synthetic.main.create_profile.*


class CreateProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_profile)

        setTitle(R.string.create_profile)

        save_profile.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}
