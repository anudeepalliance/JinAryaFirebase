package com.example.work.jinaryafirebase.People

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.work.jinaryafirebase.R
import com.example.work.jinaryafirebase.SearchPeopleActivity
import kotlinx.android.synthetic.main.activity_people.*

class PeopleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people)

        setTitle(R.string.people)

        val peopleFragmentAdapter =
                PeoplePageAdapter(supportFragmentManager)

        people_viewpager.adapter = peopleFragmentAdapter

        people_tabs.setupWithViewPager(people_viewpager)
    }

    fun intentToSearchPeople() {

        val intentToSearchPeople = Intent(this, SearchPeopleActivity::class.java)
        startActivity(intentToSearchPeople)

    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_search_people, menu)//Menu Resource, Menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.search_people_menu_item -> {
                intentToSearchPeople()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}
