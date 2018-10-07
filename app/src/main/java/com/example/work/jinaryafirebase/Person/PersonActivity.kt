package com.example.work.jinaryafirebase.Person

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.work.jinaryafirebase.CompanionObjects
import com.example.work.jinaryafirebase.R
import kotlinx.android.synthetic.main.person_content.*
import org.jetbrains.anko.startActivity

class PersonActivity : AppCompatActivity() {

    var personName = "PersonList Name"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.person_drawer)

        textView23.text = intent.getStringExtra(CompanionObjects.USER_ID_NAME_KEY)

        title = personName

        setUpIntents()

    }

    private fun setUpIntents() {

        person_compliments.setOnClickListener {
            startActivity<PersonComplimentsReceived>()
        }

        person_insights.setOnClickListener {
            startActivity<PersonInsights>()
        }

        person_followers.setOnClickListener {
            startActivity<PersonFollowers>()
        }

        person_following.setOnClickListener {
            startActivity<PersonFollowing>()
        }
    }
}
