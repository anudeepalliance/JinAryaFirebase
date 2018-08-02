package com.example.work.jinaryafirebase

import android.content.Context
import android.content.Intent

class CompanionObjects {

    companion object {

        fun intentToSearchPeopleActivity(context: Context): Intent {
            val intent = Intent(context, SearchPeopleActivity::class.java)
            return intent
        }

        fun shareApp(context: Context) : Intent {

            //TODO
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, "App")
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name)
            sendIntent.type = "text/plain"
            return sendIntent

        }
    }
}