package com.example.work.jinaryafirebase.Home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.example.work.jinaryafirebase.R
import kotlinx.android.synthetic.main.activity_home_drawer.*
import kotlinx.android.synthetic.main.activity_home_content.*
import kotlinx.android.synthetic.main.home_app_bar_main.*

class HomeActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_drawer)
        setTitle(R.string.home)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, home_drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        home_drawer.addDrawerListener(toggle)
        toggle.syncState()

        home_nav_view.setNavigationItemSelectedListener(this)

        val homeFragmentAdapter =
                HomePageAdapter(supportFragmentManager)

        home_viewpager.adapter = homeFragmentAdapter
        home_tabs.setupWithViewPager(home_viewpager)

    }

    override fun onBackPressed() {
        if (home_drawer.isDrawerOpen(GravityCompat.START)) {
            home_drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        home_drawer.closeDrawer(GravityCompat.START)
        return true
    }

}
