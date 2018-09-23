package com.example.work.jinaryafirebase.Person

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.work.jinaryafirebase.R

class PersonComplimentsReceived : AppCompatActivity() {

    var personName = "PersonList Name"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.person_compliments_received_drawer)

        title = personName + getString(R.string.compliments)
    }

}
