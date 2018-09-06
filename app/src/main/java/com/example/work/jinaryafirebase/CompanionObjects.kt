package com.example.work.jinaryafirebase

import android.content.Context
import android.content.Intent
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File


class CompanionObjects {

    companion object {

        //Collection and Document Names for Firestore Do not Change Values once added
        val usersCollection = "Users"
        val complimentsReceivedSubCollection = "ComplimentsReceived"
        val complimentsSentSubCollection = "ComplimentsSent"
        val followersSubCollection = "Followers"
        val followingSubCollection = "Following"
        val suggestedPeopleSubCollection = "Suggested"
        val myInsightsSubCollection = "MyInsights"
        val personInsightsSubCollection = "PersonInsights"
        val notificationsSubCollection = "Notifications"
        val pokesReceivedSubCollection = "PokesReceived"
        val profileInfoSubCollection = "ProfileInfo"

        var profileCreated : Boolean = false


        //firestore fields for complimentReceived, to be same as the one used in its class declaration
        val COMPLIMENT_RECEIVED_NAME_KEY = "userName"
        val COMPLIMENT_RECEIVED_UID = "uid"
        val COMPLIMENT_RECEIVED_TIME_STAMP = "timestamp"
        val COMPLIMENT_RECEIVED_CONTENT = "complimentReceivedContent"

        //firestore fields for complimentSent, to be same as the one used in its class declaration
        val COMPLIMENT_SENT_NAME_KEY = "userName"
        val COMPLIMENT_SENT_UID = "uid"
        val COMPLIMENT_SENT_TIME_STAMP = "timestamp"
        val COMPLIMENT_SENT_CONTENT = "complimentSentContent"

        //firestore fields for follower, to be same as the one used in its class declaration
        val FOLLOWER_NAME_KEY = "userName"
        val FOLLOWER_UID = "uid"

        //firestore fields for following, to be same as the one used in its class declaration
        val FOLLOWING_NAME_KEY = "userName"
        val FOLLOWING_UID = "uid"

        //firestore fields for myInsights, to be same as the one used in its class declaration
        val MY_INSIGHT_QUESTION_KEY = "myInsightQuestion"
        val MY_INSIGHT_CONTENT = "myInsightContent"
        val MY_INSIGHT_TIMESTAMP = "timestamp"

        //firestore fields for notifications, to be same as the one used in its class declaration
        val NOTIFICATION_CONTENT = "notificationContent"
        val NOTIFICATION_TIMESTAMP = "notificationDate"

        //firestore fields for personInsight, to be same as the one used in its class declaration
        val PERSON_INSIGHT_USERNAME = "userName"
        val PERSON_INSIGHT_QUESTION = "question"
        val PERSON_INSIGHT_CONTENT = "personInsightContent"
        val PERSON_INSIGHT_DATE = "timestamp"
        val PERSON_INSIGHT_USER_ID = "uid"

        //firestore fields for pokesReceived, to be same as the one used in its class declaration
        val POKE_RECEIVED_USERNAME = "userName"
        val POKE_RECEIVED_UID = "uid"

        //firestore fields for userProfile, to be same as the one used in its class declaration
        val USER_PROFILE_NAME_KEY = "userName"
        val USER_PROFILE_EMAIL_ID_KEY = "userEmail"
        val USER_PROFILE_GENDER_KEY = "userGender"
        val USER_PROFILE_DOB_KEY = "dob"
        val USER_PROFILE_RELATIONSHIP_STATUS_DOB_KEY = "relationshipStatus"
        val USER_PROFILE_COLLEGE_KEY = "colleges"
        val USER_PROFILE_WORKPLACE_KEY = "workPlaces"
        val USER_PROFILE_INTERESTS_KEY = "userInterests"
        val USER_PROFILE_CURRENT_CITY_KEY = "currentCity"
        val USER_PROFILE_HOME_TOWN_KEY = "homeTown"
        val USER_PROFILE_ABOUT_ME_KEY = "aboutMe"
        val USER_PROFILE_PROFILE_PHOTO_PATH = "profilePicturePath"
        lateinit var USER_PROFILE_PROFILE_PHOTO_URI : Uri

        //firestore fields for suggestedPeople, to be same as the one used in its class declaration
        val SUGGESTED_PEOPLE_USERNAME = "userName"
        val SUGGESTED_PEOPLE_UID = "uid"
        val SUGGESTED_PEOPLE_MUTUAL_CONNECTIONS = "mutualConnections"

        fun getComplimentsReceivedDocRef() : DocumentReference {

            return FirebaseFirestore
                    .getInstance().document(
                            "${CompanionObjects.usersCollection}/${FirebaseAuth.getInstance()
                                    .currentUser!!.uid}/${CompanionObjects.complimentsReceivedSubCollection}/${FirebaseAuth
                                    .getInstance().currentUser!!.uid}")
        }

        fun getComplimentsSentDocRef() : DocumentReference {

            return FirebaseFirestore
                    .getInstance().document(
                            "$usersCollection/${FirebaseAuth.getInstance()
                                    .currentUser!!.uid}/$complimentsSentSubCollection/${FirebaseAuth
                                    .getInstance().currentUser!!.uid}")
        }

        fun getFollowersDocRef() : DocumentReference {

            return FirebaseFirestore
                    .getInstance().document(
                            "$usersCollection/${FirebaseAuth.getInstance()
                                    .currentUser!!.uid}/$followersSubCollection/${FirebaseAuth
                                    .getInstance().currentUser!!.uid}")
        }

        fun getFollowingDoctRef() : DocumentReference {

            return FirebaseFirestore
                    .getInstance().document(
                            "$usersCollection/${FirebaseAuth.getInstance()
                                    .currentUser!!.uid}/$followingSubCollection/${FirebaseAuth
                                    .getInstance().currentUser!!.uid}")
        }

        fun getMyInsightsDocRef() : DocumentReference {

            return FirebaseFirestore
                    .getInstance().document(
                            "$usersCollection/${FirebaseAuth.getInstance()
                                    .currentUser!!.uid}/$myInsightsSubCollection/${FirebaseAuth
                                    .getInstance().currentUser!!.uid}")
        }

        fun getNotificationsDocRef() : DocumentReference {

            return FirebaseFirestore
                    .getInstance().document(
                            "$usersCollection/${FirebaseAuth.getInstance()
                                    .currentUser!!.uid}/$notificationsSubCollection/${FirebaseAuth
                                    .getInstance().currentUser!!.uid}")
        }

        fun getPersonInsightsDocRef() : DocumentReference {

            return FirebaseFirestore
                    .getInstance().document(
                            "$usersCollection/${FirebaseAuth.getInstance()
                                    .currentUser!!.uid}/$personInsightsSubCollection/${FirebaseAuth
                                    .getInstance().currentUser!!.uid}")
        }

        fun getPokesReceivedDocRef() : DocumentReference {

            return FirebaseFirestore
                    .getInstance().document(
                            "$usersCollection/${FirebaseAuth.getInstance()
                                    .currentUser!!.uid}/$pokesReceivedSubCollection/${FirebaseAuth
                                    .getInstance().currentUser!!.uid}")
        }

        fun getProfileInfoDocRef() : DocumentReference {

            return FirebaseFirestore.getInstance().document(
                    "${CompanionObjects.usersCollection}/${FirebaseAuth.getInstance()
                            .currentUser!!.uid}/${CompanionObjects.profileInfoSubCollection}/${FirebaseAuth
                            .getInstance().currentUser!!.uid}")
        }

        fun getSuggestedPeopleDocRef() : DocumentReference {

            return FirebaseFirestore
                    .getInstance().document(
                            "$usersCollection/${FirebaseAuth.getInstance()
                                    .currentUser!!.uid}/$suggestedPeopleSubCollection/${FirebaseAuth
                                    .getInstance().currentUser!!.uid}")
        }



        fun getProfileImagesFolderRef() : StorageReference {

            return FirebaseStorage.getInstance().reference.
                    child(FirebaseAuth.getInstance().currentUser!!.uid).
                    child(USER_PROFILE_PROFILE_PHOTO_PATH)
        }

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


        fun deleteFile(file:File):Boolean {
            var deletedAll = true
            if (file != null)
            {
                if (file.isDirectory())
                {
                    val children = file.list()
                    for (i in children.indices)
                    {
                        deletedAll = deleteFile(File(file, children[i])) && deletedAll
                    }
                }
                else
                {
                    deletedAll = file.delete()
                }
            }
            return deletedAll
        }




    }


}