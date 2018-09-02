package com.example.work.jinaryafirebase

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.login_app_bar_main.*
import com.firebase.ui.auth.AuthUI
import java.util.*
import com.google.firebase.auth.FirebaseAuth
import com.firebase.ui.auth.IdpResponse
import android.content.Intent
import com.example.work.jinaryafirebase.Home.HomeActivity
import kotlinx.android.synthetic.main.login_content.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class LoginActivity : AppCompatActivity() {

    private val RC_SIGN_IN = 123

    private val providers = Arrays.asList(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_drawer)

        setTitle(R.string.login)

        setSupportActionBar(login_toolbar)

        google_logo_image.setOnClickListener {
            startSignInProcess()
        }

    }

    private fun startSignInProcess() {

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setLogo(R.drawable.ic_logo_circular)
                        .setTheme(R.style.AppTheme)
                        .build(),
                        RC_SIGN_IN)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == RC_SIGN_IN) {

            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                baseContext.toast("Signed In")
                startActivity<CreateProfileActivity>()

            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
                baseContext.toast("Sign in failed, Please check your Internet Connection")
            }
        }

    }

}
