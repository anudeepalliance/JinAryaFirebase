package com.example.work.jinaryafirebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_search_people.*
import android.view.View.*


class SearchPeopleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_people)
        setTitle(R.string.search_people)

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
