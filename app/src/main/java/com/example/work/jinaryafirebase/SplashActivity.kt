package com.example.work.jinaryafirebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.work.jinaryafirebase.CompanionObjects.Companion.userSigned
import com.example.work.jinaryafirebase.Home.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        when {
            //
            userSigned
            -> startActivity<HomeActivity>()
            else
            -> startActivity<LoginActivity>()
        }

        finish()
    }
}
