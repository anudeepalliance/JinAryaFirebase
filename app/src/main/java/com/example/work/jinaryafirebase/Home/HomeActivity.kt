package com.example.work.jinaryafirebase.Home

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.example.work.jinaryafirebase.CompanionObjects.Companion.intentToComplimentsActivity
import com.example.work.jinaryafirebase.CompanionObjects.Companion.intentToHelpActivity
import com.example.work.jinaryafirebase.CompanionObjects.Companion.intentToInsightsActivity
import com.example.work.jinaryafirebase.CompanionObjects.Companion.intentToPeopleActivity
import com.example.work.jinaryafirebase.CompanionObjects.Companion.intentToPrivacyPolicy
import com.example.work.jinaryafirebase.CompanionObjects.Companion.intentToSettingsActivity
import com.example.work.jinaryafirebase.CompanionObjects.Companion.sendFeedbackIntent
import com.example.work.jinaryafirebase.CompanionObjects.Companion.shareApp
import com.example.work.jinaryafirebase.R
import kotlinx.android.synthetic.main.home_drawer.*
import kotlinx.android.synthetic.main.home_content.*
import kotlinx.android.synthetic.main.home_app_bar_main.*
import com.example.work.jinaryafirebase.Classes.UserProfile
import com.example.work.jinaryafirebase.CompanionObjects.Companion.profileImagesFolderRef
import com.example.work.jinaryafirebase.CompanionObjects.Companion.profileInfoDocumentReference
import com.example.work.jinaryafirebase.LoginActivity
import com.example.work.jinaryafirebase.R.drawable.ic_logo_circular
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.storage.images.FirebaseImageLoader
import kotlinx.android.synthetic.main.nav_header_main.*
import org.jetbrains.anko.startActivity
import java.io.File
import com.google.common.collect.TreeTraverser.using
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_my_page_fragment.*
import org.jetbrains.anko.find
import org.jetbrains.anko.image
import java.io.InputStream


class HomeActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_drawer)
        setTitle(R.string.home)
        populateUserInfo()

        setSupportActionBar(home_toolbar)

        val toggle = ActionBarDrawerToggle(
                this, home_drawer_main, home_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        home_drawer_main.addDrawerListener(toggle)
        toggle.syncState()

        home_nav_view.setNavigationItemSelectedListener(this)

        val homeFragmentAdapter =
                HomePageAdapter(supportFragmentManager)

        home_viewpager.adapter = homeFragmentAdapter

    }

    override fun onBackPressed() {
        if (home_drawer_main.isDrawerOpen(GravityCompat.START)) {
            home_drawer_main.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }





    private fun populateUserInfo() {

        profileInfoDocumentReference.get().addOnSuccessListener { DocumentSnapshot ->

            val userProfileInfo = DocumentSnapshot.toObject(UserProfile::class.java)
            if (userProfileInfo != null) {
                name_text.text = userProfileInfo.userName
                email_text.text = userProfileInfo.userEmail
//                profile_photo_image.image =
            }

        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.home_item -> {
                //nothing
            }

            R.id.people_item -> {
                startActivity(intentToPeopleActivity(this))
            }

            R.id.compliments_item -> {
                startActivity(intentToComplimentsActivity(this))
            }

            R.id.insights_item -> {
                startActivity(intentToInsightsActivity(this))
            }

            R.id.settings_item -> {
                startActivity(intentToSettingsActivity(this))
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
                startActivity(intentToHelpActivity(this))
            }

            R.id.privacy_policy_item -> {
                startActivity(intentToPrivacyPolicy(this))
            }

            R.id.sign_out_item -> {
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener {
                            startActivity<LoginActivity>()
                        }
            }
        }

        home_drawer_main.closeDrawer(GravityCompat.START)
        return true
    }

}
