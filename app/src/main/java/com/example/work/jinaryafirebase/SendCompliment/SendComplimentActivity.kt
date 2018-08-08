package com.example.work.jinaryafirebase.SendCompliment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.work.jinaryafirebase.R
import kotlinx.android.synthetic.main.send_compliment_app_bar_main.*

class SendComplimentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.send_compliment_drawer)

        setTitle(R.string.send_a_compliment)

        setSupportActionBar(send_compliment_toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}
