package com.example.work.jinaryafirebase

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.work.jinaryafirebase.Home.HomeActivity
import kotlinx.android.synthetic.main.create_profile_content.*
import kotlinx.android.synthetic.main.create_profile_app_bar_main.*
import android.net.Uri
import com.example.work.jinaryafirebase.CompanionObjects.Companion.COMPLIMENT_RECEIVED_CONTENT
import com.example.work.jinaryafirebase.CompanionObjects.Companion.COMPLIMENT_RECEIVED_NAME_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.COMPLIMENT_RECEIVED_TIME_STAMP
import com.example.work.jinaryafirebase.CompanionObjects.Companion.COMPLIMENT_RECEIVED_UID
import com.example.work.jinaryafirebase.CompanionObjects.Companion.COMPLIMENT_SENT_CONTENT
import com.example.work.jinaryafirebase.CompanionObjects.Companion.COMPLIMENT_SENT_NAME_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.COMPLIMENT_SENT_TIME_STAMP
import com.example.work.jinaryafirebase.CompanionObjects.Companion.COMPLIMENT_SENT_UID
import com.example.work.jinaryafirebase.CompanionObjects.Companion.FOLLOWER_NAME_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.FOLLOWER_UID
import com.example.work.jinaryafirebase.CompanionObjects.Companion.FOLLOWING_NAME_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.FOLLOWING_UID
import com.example.work.jinaryafirebase.CompanionObjects.Companion.MY_INSIGHT_CONTENT
import com.example.work.jinaryafirebase.CompanionObjects.Companion.MY_INSIGHT_QUESTION_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.MY_INSIGHT_TIMESTAMP
import com.example.work.jinaryafirebase.CompanionObjects.Companion.NOTIFICATION_CONTENT
import com.example.work.jinaryafirebase.CompanionObjects.Companion.NOTIFICATION_TIMESTAMP
import com.example.work.jinaryafirebase.CompanionObjects.Companion.PERSON_INSIGHT_CONTENT
import com.example.work.jinaryafirebase.CompanionObjects.Companion.PERSON_INSIGHT_DATE
import com.example.work.jinaryafirebase.CompanionObjects.Companion.PERSON_INSIGHT_QUESTION
import com.example.work.jinaryafirebase.CompanionObjects.Companion.PERSON_INSIGHT_USERNAME
import com.example.work.jinaryafirebase.CompanionObjects.Companion.PERSON_INSIGHT_USER_ID
import com.example.work.jinaryafirebase.CompanionObjects.Companion.POKE_RECEIVED_UID
import com.example.work.jinaryafirebase.CompanionObjects.Companion.POKE_RECEIVED_USERNAME
import com.example.work.jinaryafirebase.CompanionObjects.Companion.SUGGESTED_PEOPLE_MUTUAL_CONNECTIONS
import com.example.work.jinaryafirebase.CompanionObjects.Companion.SUGGESTED_PEOPLE_UID
import com.example.work.jinaryafirebase.CompanionObjects.Companion.SUGGESTED_PEOPLE_USERNAME
import com.example.work.jinaryafirebase.CompanionObjects.Companion.USER_ID_NAME_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.USER_PROFILE_ABOUT_ME_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.USER_PROFILE_COLLEGE_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.USER_PROFILE_CURRENT_CITY_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.USER_PROFILE_DOB_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.USER_PROFILE_EMAIL_ID_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.USER_PROFILE_GENDER_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.USER_PROFILE_HOME_TOWN_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.USER_PROFILE_INTERESTS_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.USER_PROFILE_NAME_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.USER_PROFILE_PROFILE_PHOTO_PATH
import com.example.work.jinaryafirebase.CompanionObjects.Companion.USER_PROFILE_RELATIONSHIP_STATUS_DOB_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.USER_PROFILE_WORKPLACE_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.getComplimentsSentDocRef
import com.example.work.jinaryafirebase.CompanionObjects.Companion.getFollowersDocRef
import com.example.work.jinaryafirebase.CompanionObjects.Companion.getFollowingDocRef
import com.example.work.jinaryafirebase.CompanionObjects.Companion.getMyInsightsDocRef
import com.example.work.jinaryafirebase.CompanionObjects.Companion.getNotificationsDocRef
import com.example.work.jinaryafirebase.CompanionObjects.Companion.getPersonInsightsDocRef
import com.example.work.jinaryafirebase.CompanionObjects.Companion.getPokesReceivedDocRef
import com.example.work.jinaryafirebase.CompanionObjects.Companion.getProfileImagesFolderRef
import com.example.work.jinaryafirebase.CompanionObjects.Companion.getSuggestedPeopleDocRef
import com.example.work.jinaryafirebase.CompanionObjects.Companion.getUsersListRef
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.*
import java.io.ByteArrayOutputStream
import kotlin.collections.HashMap


class CreateProfileActivity : AppCompatActivity() {

    val RC_PHOTO_PICKER = 2
    private var insanityCheckPassed : Boolean = false
    private var photoSelected = false
    lateinit var filePath : Uri

    var photoUri : Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.create_profile_drawer)

        setTitle(R.string.create_profile)
        setSupportActionBar(create_profile_toolbar)

        save_profile_button.setOnClickListener {
            createAndSaveProfile()
        }

        home_profile_photo.setOnClickListener {
            profilePhotoSelector()
        }

        //TODO change this to a proper placeholder image
        home_profile_photo.setImageResource(R.drawable.ic_logo_circular)

    }

    private fun profilePhotoSelector() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
        startActivityForResult(Intent.createChooser(intent, "Complete action using"),
                this.RC_PHOTO_PICKER)
    }

    private fun insanityCheckPassed() {

        if ( name_edit_text.text.isNotBlank() && gender_spinner.selectedItemPosition > 0 &&
                date_picker.date != null && college_edit_text.text.isNotBlank() &&
                workplace_edit_text.text.isNotBlank() && interests_edit_text.text.isNotBlank() &&
                current_city_edit_text.text.isNotBlank() && hometown_edit_text.text.isNotBlank() &&
                about_edit_text.text.isNotBlank() ) {
            insanityCheckPassed = true

        } else {

            //TODO add the functionality to say which field is missing
            insanityCheckPassed = false
            baseContext.toast("Please Enter all Fields")
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if ( requestCode == this.RC_PHOTO_PICKER && resultCode == Activity.RESULT_OK &&
                data != null && data.data != null ) {

            try {
                val selectedImageUri : Uri = data.data
                val imageStream = contentResolver.openInputStream(selectedImageUri)
                val selectedImage = BitmapFactory.decodeStream(imageStream)
                home_profile_photo.imageBitmap = selectedImage
                photoSelected = true
            } catch (e : Exception) {
                baseContext.toast("File not Found, Please select another Image")
            }
        } else {
            baseContext.toast("Pick an Image to set a Profile photo")
        }

        when ( requestCode ) {
            RC_PHOTO_PICKER -> {
                if (data != null) {
                    filePath = data.data
                }
            }
        }

    }

    private fun alertIfPhotoNotSelected() {
        if ( !photoSelected ) {
            alert("", "Are you sure you want to continue without setting" +
                    "a profile photo ?")  {
                yesButton {
                    baseContext.toast("Oh…")
                    createAndSaveProfile()
                }
                //TODO customise the no button text
                noButton { profilePhotoSelector() }
            }.show()
        }
    }

    private fun profilePhotoUpload() {


        if (photoSelected) {

            home_profile_photo.isDrawingCacheEnabled = true
            home_profile_photo.buildDrawingCache()
            val bitmap = (home_profile_photo.drawable as BitmapDrawable).bitmap
            val profilePhoto = ByteArrayOutputStream()

            //TODO reduce size of selected image before uploading Storage
            bitmap.compress(Bitmap.CompressFormat.WEBP, 40, profilePhoto)
            val data = profilePhoto.toByteArray()

            val uploadTask = getProfileImagesFolderRef().putFile(filePath)

            uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    throw task.exception!!
                }

                // Continue with the task to get the download URL
                getProfileImagesFolderRef().downloadUrl
            }.addOnCompleteListener { task -> 
                if (task.isSuccessful) {
                    photoUri = task.result

                } else {
                    // Handle failures
                    // ...
                }
            }

        }
    }

    private fun createAndSaveProfile() {

        alertIfPhotoNotSelected()

        insanityCheckPassed()

        if ( insanityCheckPassed ) {

            profilePhotoUpload()


            // add dummy compliment Received Sub Collection
            try {
                val dummyComplimentReceived = HashMap<Any, Any?>()

                dummyComplimentReceived[COMPLIMENT_RECEIVED_NAME_KEY] = "compliment Sender Name"
                dummyComplimentReceived[COMPLIMENT_RECEIVED_UID] = "compliment Sender uid"
                dummyComplimentReceived[COMPLIMENT_RECEIVED_TIME_STAMP] = "compliment Received Timestamp"
                dummyComplimentReceived[COMPLIMENT_RECEIVED_CONTENT] = "compliment Received Content"

                CompanionObjects.getComplimentsReceivedDocRef().set(dummyComplimentReceived)
                        .addOnSuccessListener {
                    baseContext.toast("dummyComplimentReceived Saved Successfully")
                }
            } catch (e : Exception) {
                baseContext.toast(e.toString())
            }

            // add dummy compliment Sent Sub Collection
            try {
                val dummyComplimentSent = HashMap<Any, Any?>()

                dummyComplimentSent[COMPLIMENT_SENT_NAME_KEY] = "compliment Sent To Name"
                dummyComplimentSent[COMPLIMENT_SENT_UID] = "compliment Sent To uid"
                dummyComplimentSent[COMPLIMENT_SENT_TIME_STAMP] = "compliment Sent Timestamp"
                dummyComplimentSent[COMPLIMENT_SENT_CONTENT] = "compliment Sent Content"

                getComplimentsSentDocRef().set(dummyComplimentSent).addOnSuccessListener {
                    baseContext.toast("dummyComplimentSent Saved Successfully")
                }
            } catch (e : Exception) {
                baseContext.toast(e.toString())
            }

            // add dummy Followers Sub Collection
            try {
                val dummyFollower = HashMap<Any, Any?>()

                dummyFollower[FOLLOWER_NAME_KEY] = "follower Name"
                dummyFollower[FOLLOWER_UID] = "follower uid"

                getFollowersDocRef().set(dummyFollower).addOnSuccessListener {
                    baseContext.toast("dummyFollower Saved Successfully")
                }
            } catch (e : Exception) {
                baseContext.toast(e.toString())
            }

            // add dummy following Sub Collection
            try {

                val dummyFollowing = HashMap<Any, Any?>()

                dummyFollowing[FOLLOWING_NAME_KEY] = "following Name"
                dummyFollowing[FOLLOWING_UID] = "following uid"

                getFollowingDocRef().set(dummyFollowing).addOnSuccessListener {
                    baseContext.toast("dummyFollowing Saved Successfully")
                }
            } catch (e : Exception) {
                baseContext.toast(e.toString())
            }

            // add dummy MyInsight Sub Collection
            try {

                val dummyMyInsight = HashMap<Any, Any?>()

                dummyMyInsight[MY_INSIGHT_QUESTION_KEY] = "myInsightQuestion"
                dummyMyInsight[MY_INSIGHT_CONTENT] = "myInsightContent"
                dummyMyInsight[MY_INSIGHT_TIMESTAMP] = "timestamp"

                getMyInsightsDocRef().set(dummyMyInsight).addOnSuccessListener {
                    baseContext.toast("dummyMyInsight Saved Successfully")
                }
            } catch (e : Exception) {
                baseContext.toast(e.toString())
            }

            // add dummy Notification Sub Collection
            try {

                val dummyNotification = HashMap<Any, Any?>()

                dummyNotification[NOTIFICATION_CONTENT] = "notificationContent"
                dummyNotification[NOTIFICATION_TIMESTAMP] = "notificationDate"

                getNotificationsDocRef().set(dummyNotification).addOnSuccessListener {
                    baseContext.toast("dummyNotification Saved Successfully")
                }
            } catch (e : Exception) {
                baseContext.toast(e.toString())
            }

            // add dummy personInsights Sub Collection
            try {

                val dummyPersonInsights = HashMap<Any, Any?>()

                dummyPersonInsights[PERSON_INSIGHT_USERNAME] = "userName"
                dummyPersonInsights[PERSON_INSIGHT_QUESTION] = "question"
                dummyPersonInsights[PERSON_INSIGHT_CONTENT] = "personInsightContent"
                dummyPersonInsights[PERSON_INSIGHT_DATE] = "timestamp"
                dummyPersonInsights[PERSON_INSIGHT_USER_ID] = "uid"

                getPersonInsightsDocRef().set(dummyPersonInsights).addOnSuccessListener {
                    baseContext.toast("personInsights Saved Successfully")
                }
            } catch (e : Exception) {
                baseContext.toast(e.toString())
            }

            // add dummy pokesReceived Sub Collection
            try {

                val dummyPokesReceived = HashMap<Any, Any?>()

                dummyPokesReceived[POKE_RECEIVED_USERNAME] = "userName"
                dummyPokesReceived[POKE_RECEIVED_UID] = "uid"

                getPokesReceivedDocRef().set(dummyPokesReceived).addOnSuccessListener {
                    baseContext.toast("dummyPokesReceived Saved Successfully")
                }
            } catch (e : Exception) {
                baseContext.toast(e.toString())
            }


            // add userProfile
            try {

                val profileInfo = HashMap<Any, Any?>()

                profileInfo[USER_PROFILE_NAME_KEY] = name_edit_text.text.toString().trim()
                profileInfo[USER_PROFILE_EMAIL_ID_KEY] = FirebaseAuth.getInstance().currentUser!!.email.toString()
                profileInfo[USER_PROFILE_GENDER_KEY] = gender_spinner.selectedItem.toString()
                profileInfo[USER_PROFILE_DOB_KEY] = date_picker.date
                profileInfo[USER_PROFILE_RELATIONSHIP_STATUS_DOB_KEY] = relationship_status_spinner.selectedItem.toString()
                profileInfo[USER_PROFILE_COLLEGE_KEY] = college_edit_text.text.toString().trim()
                profileInfo[USER_PROFILE_WORKPLACE_KEY] = workplace_edit_text.text.toString().trim()
                profileInfo[USER_PROFILE_INTERESTS_KEY] = interests_edit_text.text.toString().trim()
                profileInfo[USER_PROFILE_CURRENT_CITY_KEY] = current_city_edit_text.text.toString().trim()
                profileInfo[USER_PROFILE_HOME_TOWN_KEY] = hometown_edit_text.text.toString().trim()
                profileInfo[USER_PROFILE_ABOUT_ME_KEY] = about_edit_text.text.toString().trim()
                profileInfo[USER_PROFILE_PROFILE_PHOTO_PATH] = "another something"


                CompanionObjects.getProfileInfoDocRef().set(profileInfo).addOnSuccessListener {
                    baseContext.toast("Profile Saved Successfully")
                    startActivity<HomeActivity>()
                }.addOnFailureListener {
                    baseContext.toast("unable to save, please try again later, $it")
                }

            } catch (e: Exception) {
                baseContext.toast(e.toString())
            }

            // add dummy Suggested Sub Collection
            try {

                val dummySuggestedPeople = HashMap<Any, Any?>()

                dummySuggestedPeople[SUGGESTED_PEOPLE_USERNAME] = "userName"
                dummySuggestedPeople[SUGGESTED_PEOPLE_UID] = "uid"
                dummySuggestedPeople[SUGGESTED_PEOPLE_MUTUAL_CONNECTIONS] = 0

                getSuggestedPeopleDocRef().set(dummySuggestedPeople).addOnSuccessListener {
                    baseContext.toast("dummySuggestedPeople Saved Successfully")
                }
            } catch (e : Exception) {
                baseContext.toast(e.toString())
            }

            // create the user in usersList collection
            try {

                val usersList = HashMap<Any, Any?>()

                usersList[USER_ID_NAME_KEY] = FirebaseAuth.getInstance().currentUser!!.uid
                usersList[USER_PROFILE_NAME_KEY] = name_edit_text.text.toString().trim()
                usersList[USER_PROFILE_CURRENT_CITY_KEY] = current_city_edit_text.text.toString().trim()

                getUsersListRef().set(usersList).addOnSuccessListener {
                    baseContext.toast("usersList Saved Successfully")
                }
            } catch (e : Exception) {
                baseContext.toast(e.toString())
            }

        }
    }


}