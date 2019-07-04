package com.example.work.jinaryafirebase.Home

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.work.jinaryafirebase.*
import com.example.work.jinaryafirebase.CompanionObjects.Companion.intentToPrivacyPolicy
import com.example.work.jinaryafirebase.CompanionObjects.Companion.sendFeedbackIntent
import com.example.work.jinaryafirebase.CompanionObjects.Companion.shareApp
import kotlinx.android.synthetic.main.home_drawer.*
import kotlinx.android.synthetic.main.home_content.*
import kotlinx.android.synthetic.main.home_app_bar_main.*
import com.example.work.jinaryafirebase.Classes.ProfileInfo
import com.example.work.jinaryafirebase.CompanionObjects.Companion.getProfileInfoDocRef
import com.example.work.jinaryafirebase.CompanionObjects.Companion.userSigned
import com.example.work.jinaryafirebase.Compliments.ComplimentsActivity
import com.example.work.jinaryafirebase.Insights.InsightsActivity
import com.example.work.jinaryafirebase.People.PeopleActivity
import com.example.work.jinaryafirebase.Settings.SettingsActivity
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.nav_header_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class HomeActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkIfProfileExists()

    }

    override fun onBackPressed() {

        if (home_drawer_main.isDrawerOpen(GravityCompat.START)) {

            home_drawer_main.closeDrawer(GravityCompat.START)

        } else {

            super.onBackPressed()

        }

    }

    private fun checkIfProfileExists() {

        getProfileInfoDocRef().get().addOnSuccessListener { DocumentSnapshot ->

            if (!DocumentSnapshot.exists()) {

                startActivity<CreateProfileActivity>()
                finish()

            } else {

                setContentView(R.layout.home_drawer)
                setTitle(R.string.home)
                setSupportActionBar(home_toolbar)
                val toggle = ActionBarDrawerToggle(
                        this, home_drawer_main, home_toolbar,
                        R.string.navigation_drawer_open, R.string.navigation_drawer_close)

                home_drawer_main.addDrawerListener(toggle)
                toggle.syncState()
                populateUserInfo()
                home_nav_view.setNavigationItemSelectedListener(this)
                val homeFragmentAdapter = HomePageAdapter(supportFragmentManager)
                home_viewpager.adapter = homeFragmentAdapter
                home_tabs.setupWithViewPager(home_viewpager)

                }

            }

    }

    private fun populateUserInfo() {

        getProfileInfoDocRef().get().addOnSuccessListener { DocumentSnapshot ->

            if (DocumentSnapshot.exists()) {

                val userProfileInfo = DocumentSnapshot.toObject(ProfileInfo::class.java)
                if (userProfileInfo != null) {
                    name_text.text = userProfileInfo.userName
                    email_text.text = userProfileInfo.userEmail
                }
            }
        }

        populateUserPhoto()
    }

    private fun populateUserPhoto() {


        CompanionObjects.getProfileImagesFolderRef().downloadUrl.addOnSuccessListener {

            val downloadUrl = it.toString()

            Glide.with(this@HomeActivity)
                    .load(downloadUrl)
                    .into(home_profile_photo)

        }.addOnFailureListener {
            baseContext.toast("unable to retrieve your profile photo")
        }

    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.home_item -> {
                //nothing
            }

            R.id.people_item -> {
                startActivity<PeopleActivity>()
//                startActivity(intentToPeopleActivity(this))
            }

            R.id.compliments_item -> {
                startActivity<ComplimentsActivity>()
            }

            R.id.insights_item -> {
                startActivity<InsightsActivity>()
            }

            R.id.settings_item -> {
                startActivity<SettingsActivity>()
            }

            R.id.share_app_item -> {
                startActivity(Intent.createChooser(shareApp(this),
                        getString(R.string.share_app_title)))
            }

            R.id.send_feedback_tem -> {
                startActivity(Intent.createChooser(sendFeedbackIntent(this),
                        getString(R.string.email_title)))
            }

            R.id.help_item -> {
                startActivity<HelpActivity>()
            }

            R.id.privacy_policy_item -> {
                startActivity(intentToPrivacyPolicy(this))
            }

            R.id.sign_out_item -> {
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener {
                            startActivity<LoginActivity>()
                            userSigned = false
                        }
            }

        }

        home_drawer_main.closeDrawer(GravityCompat.START)
        return true
    }

}
