package com.example.work.jinaryafirebase.Person

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.work.jinaryafirebase.People.PeopleActivity
import com.example.work.jinaryafirebase.R

class PersonComplimentsReceived : AppCompatActivity() {

    var personName = "Person Name"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_compliments_received)

        title = personName + getString(R.string.compliments)
    }

}
