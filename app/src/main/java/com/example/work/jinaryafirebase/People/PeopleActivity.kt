package com.example.work.jinaryafirebase.People

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.work.jinaryafirebase.CompanionObjects
import com.example.work.jinaryafirebase.CompanionObjects.Companion.intentToSearchPeopleActivity
import com.example.work.jinaryafirebase.R
import com.example.work.jinaryafirebase.R.layout.people_app_bar_main
import com.example.work.jinaryafirebase.R.layout.people_drawer
import kotlinx.android.synthetic.main.home_app_bar_main.*
import kotlinx.android.synthetic.main.home_drawer.*
import kotlinx.android.synthetic.main.people_app_bar_main.*
import kotlinx.android.synthetic.main.people_content.*
import kotlinx.android.synthetic.main.people_drawer.*

class PeopleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.people_drawer)

        setTitle(R.string.people)

        setSupportActionBar(people_toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val peopleFragmentAdapter =
                PeoplePageAdapter(supportFragmentManager)

        people_viewpager.adapter = peopleFragmentAdapter

        people_tabs.setupWithViewPager(people_viewpager)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_search_people, menu)//Menu Resource, Menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.search_people_menu_item -> {
                startActivity(intentToSearchPeopleActivity(this))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
