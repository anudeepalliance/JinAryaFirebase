package com.example.work.jinaryafirebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.help_app_bar_main.*

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.help_drawer)

        setTitle(R.string.help)

        setSupportActionBar(help_toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}
