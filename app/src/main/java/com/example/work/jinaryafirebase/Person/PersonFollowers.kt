package com.example.work.jinaryafirebase.Person

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.work.jinaryafirebase.R
import com.example.work.jinaryafirebase.SearchPeople.SearchPeopleActivity
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity

class PersonFollowers : AppCompatActivity() {

    var personName = "PersonList Name"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.person_followers_drawer)

        title = personName + getString(R.string.followers)
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
