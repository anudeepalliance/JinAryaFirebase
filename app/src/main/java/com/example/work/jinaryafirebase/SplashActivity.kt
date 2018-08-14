package com.example.work.jinaryafirebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.work.jinaryafirebase.CompanionObjects.Companion.profileCreated
import com.example.work.jinaryafirebase.Home.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (FirebaseAuth.getInstance().currentUser == null)
            startActivity<LoginActivity>()
        else if (FirebaseAuth.getInstance().currentUser != null && !profileCreated)
            startActivity<CreateProfileActivity>()
        else
            startActivity<HomeActivity>()
        finish()
    }
}
