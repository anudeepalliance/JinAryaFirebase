package com.example.work.jinaryafirebase

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.work.jinaryafirebase.Compliments.ComplimentsActivity
import com.example.work.jinaryafirebase.Insights.InsightsActivity
import com.example.work.jinaryafirebase.People.PeopleActivity
import com.example.work.jinaryafirebase.Settings.SettingsActivity

class CompanionObjects {

    companion object {

        fun intentToSearchPeopleActivity(context: Context): Intent {
            val intent = Intent(context, SearchPeopleActivity::class.java)
            return intent
        }

        fun intentToPeopleActivity(context: Context): Intent {
            val intent = Intent(context, PeopleActivity::class.java)
            return intent
        }

        fun intentToComplimentsActivity(context: Context): Intent {
            val intent = Intent(context, ComplimentsActivity::class.java)
            return intent
        }

        fun intentToInsightsActivity(context: Context): Intent {
            val intent = Intent(context, InsightsActivity::class.java)
            return intent
        }

        fun intentToSettingsActivity(context: Context): Intent {
            val intent = Intent(context, SettingsActivity::class.java)
            return intent
        }

        fun sendFeedbackIntent(context: Context) : Intent {
            //TODO replace the content with getStrings
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/html"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("adp989@gmail.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT,"email subject")
            intent.putExtra(Intent.EXTRA_TEXT, "email body")
            return intent

        }

        fun shareApp(context: Context) : Intent {
            //TODO add the play store link of the app here
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out this app : play.google.com")
            sendIntent.type = "text/plain"
            return sendIntent

        }

        fun intentToHelpActivity(context: Context): Intent {
            val intent = Intent(context, HelpActivity::class.java)
            return intent
        }

        fun intentToPrivacyPolicy(context: Context): Intent {
            val url = "http://www.google.com"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            return intent
        }

    }


}