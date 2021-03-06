package com.example.work.jinaryafirebase.People

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.work.jinaryafirebase.R
import com.example.work.jinaryafirebase.SearchPeople.SearchPeopleActivity
import kotlinx.android.synthetic.main.people_app_bar_main.*
import kotlinx.android.synthetic.main.people_content.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity

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
                startActivity<SearchPeopleActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
