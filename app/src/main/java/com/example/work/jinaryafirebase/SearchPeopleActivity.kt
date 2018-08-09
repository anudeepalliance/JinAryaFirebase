package com.example.work.jinaryafirebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.search_people_content.*
import android.view.View.*
import kotlinx.android.synthetic.main.search_people_bar_main.*


class SearchPeopleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_people_drawer)
        setTitle(R.string.search_people)

        setSupportActionBar(search_people_toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        configureClearButton()


    }

    private fun configureClearButton() {

        type_person_name_edit_text.setOnClickListener {

            person_name_search_clear.visibility = VISIBLE
        }

        person_name_search_clear.setOnClickListener {
            type_person_name_edit_text.setText("")
        }
    }
}
