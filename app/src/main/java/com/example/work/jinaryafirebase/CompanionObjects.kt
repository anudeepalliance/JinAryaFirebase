package com.example.work.jinaryafirebase

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import com.example.work.jinaryafirebase.AddInsight.InsightAdd
import com.example.work.jinaryafirebase.AddInsight.InsightQuestionsActivity
import com.example.work.jinaryafirebase.Compliments.ComplimentsActivity
import com.example.work.jinaryafirebase.Home.HomeActivity
import com.example.work.jinaryafirebase.Insights.InsightsActivity
import com.example.work.jinaryafirebase.People.PeopleActivity
import com.example.work.jinaryafirebase.SendCompliment.FollowingPeopleSendCompliment
import com.example.work.jinaryafirebase.SendCompliment.SendComplimentActivity
import com.example.work.jinaryafirebase.Settings.SettingsActivity



class CompanionObjects {

    companion object {

        //Collection and Document Names for Firestore Do not Change Values once added
        val usersCollection = "Users"
        val complimentsReceivedSubCollection = "ComplimentsReceived"
        val complimentsSentSubCollection = "ComplimentsSent"
        val followersSubCollection = "Followers"
        val FollowingSubCollection = "Following"
        val suggestedSubCollection = "Suggested"
        val myInsightsSubCollection = "MyInsights"
        val personInsightsSubCollection = "PersonInsights"
        val notificationsSubCollection = "Notifications"
        val pokesReceivedSubCollection = "PokesReceived"
        val profileInfoSubCollection = "ProfileInfo"
        val profileInfoSubCollectionDocument = "ProfileInfoDocumentId"

        var profileCreated : Boolean = false

        val profilePhotoFolder = "Profile_photos"

        val NAME_KEY = "Name"
        val EMAIL_ID_KEY = "Name"
        val GENDER_KEY = "Gender"
        val DOB_KEY = "DOB"
        val COLLEGE_KEY = "College"
        val WORKPLACE_KEY = "Workplace"
        val INTERESTS_KEY = "Interests"
        val CURRENT_CITY_KEY = "Current_City"
        val HOME_TOWN_KEY = "HomeTown"
        val ABOUT_ME_KEY = "About_Me"
        val PROFILE_PHOTO_PATH = "Profile_photo"



        fun intentToSearchPeopleActivity(context: Context): Intent {
            val intent = Intent(context, SearchPeopleActivity::class.java)
            return intent
        }

        fun intentToHomeActivity(context: Context): Intent {
            val intent = Intent(context, HomeActivity::class.java)
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

        fun intentToFollowingPeopleActivity(context: Context): Intent {
            val intent = Intent(context, FollowingPeopleSendCompliment::class.java)
            return intent
        }

        fun intentToSendComplimentActivity(context: Context): Intent {
            val intent = Intent(context, SendComplimentActivity::class.java)
            return intent
        }

        fun intentToAddInsightActivity(context: Context): Intent {
            val intent = Intent(context, InsightAdd::class.java)
            return intent
        }

        fun intentToInsightQuestionsActivity(context: Context): Intent {
            val intent = Intent(context, InsightQuestionsActivity::class.java)
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