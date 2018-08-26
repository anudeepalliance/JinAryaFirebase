package com.example.work.jinaryafirebase

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.work.jinaryafirebase.Home.HomeActivity
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.create_profile_content.*
import kotlinx.android.synthetic.main.create_profile_app_bar_main.*
import android.net.Uri
import com.example.work.jinaryafirebase.CompanionObjects.Companion.ABOUT_ME_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.COLLEGE_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.CURRENT_CITY_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.DOB_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.EMAIL_ID_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.GENDER_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.HOME_TOWN_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.INTERESTS_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.NAME_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.PROFILE_PHOTO_PATH
import com.example.work.jinaryafirebase.CompanionObjects.Companion.RELATIONSHIP_STATUS_DOB_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.WORKPLACE_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.profileInfoDocumentReference
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.*
import java.io.ByteArrayOutputStream
import kotlin.collections.HashMap


class CreateProfileActivity : AppCompatActivity() {

    val RC_PHOTO_PICKER = 2
    private val storage = FirebaseStorage.getInstance()
    private var profileImagesFolderRef = storage.reference.
            child(FirebaseAuth.getInstance().currentUser!!.uid).
            child(PROFILE_PHOTO_PATH)
    private var insanityCheckPassed : Boolean = false
    private var profilePhotoDownloadString :String = ""
    private var photoSelected = false
    lateinit var filePath : Uri
    private var profilePhotoDownloadUri : Uri? = null
    val profileInfo = HashMap<Any, Any?>()

    var photoUri : Uri? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_profile_drawer)

        setTitle(R.string.create_profile)
        setSupportActionBar(create_profile_toolbar)

        save_profile_button.setOnClickListener {
            createAndSaveProfile()
        }

        profile_photo_image.setOnClickListener {
            profilePhotoSelector()
        }
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
                profile_photo_image.imageBitmap = selectedImage
                photoSelected = true
            } catch (e : Exception) {
                baseContext.toast("File not Found, Please select another Image")
            }
        } else {
            baseContext.toast("Pick an Image to set a Profile photo")
        }

        when ( requestCode ) {
            RC_PHOTO_PICKER -> {
                filePath = data!!.data
            }
        }
    }

    private fun alertIfPhotoNotSelected() {
        if ( !photoSelected ) {
            alert("", "Are you sure you want to continue without setting" +
                    "a profile photo ?") {
                yesButton {
                    baseContext.toast("Oh…")
                    createAndSaveProfile()
                }
                noButton { profilePhotoSelector() }
            }.show()
        }
    }

    private fun profilePhotoUpload() {


        if (photoSelected) {

            profile_photo_image.isDrawingCacheEnabled = true
            profile_photo_image.buildDrawingCache()
            val bitmap = (profile_photo_image.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            val uploadTask = profileImagesFolderRef.putFile(filePath)

            uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    throw task.exception!!
                }

                // Continue with the task to get the download URL
                profileImagesFolderRef.downloadUrl
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

        insanityCheckPassed()

        if ( insanityCheckPassed ) {

            profilePhotoUpload()

            try {
                profileInfo[NAME_KEY] = name_edit_text.text.toString().trim()
                profileInfo[EMAIL_ID_KEY] = FirebaseAuth.getInstance().currentUser!!.email.toString()
                profileInfo[GENDER_KEY] = gender_spinner.selectedItem.toString()
                profileInfo[DOB_KEY] = date_picker.date
                profileInfo[RELATIONSHIP_STATUS_DOB_KEY] = relationship_status_spinner.selectedItem.toString()
                profileInfo[COLLEGE_KEY] = college_edit_text.text.toString().trim()
                profileInfo[WORKPLACE_KEY] = workplace_edit_text.text.toString().trim()
                profileInfo[INTERESTS_KEY] = interests_edit_text.text.toString().trim()
                profileInfo[CURRENT_CITY_KEY] = current_city_edit_text.text.toString().trim()
                profileInfo[HOME_TOWN_KEY] = hometown_edit_text.text.toString().trim()
                profileInfo[ABOUT_ME_KEY] = about_edit_text.text.toString().trim()
                profileInfo[PROFILE_PHOTO_PATH] = "another something"

                profileInfoDocumentReference.set(profileInfo).addOnSuccessListener {
                    baseContext.toast("Profile Saved Successfully")
                    CompanionObjects.profileCreated = true
                    startActivity<HomeActivity>()
                }.addOnFailureListener {
                    baseContext.toast("unable to save, please try again later, $it")
                }

            } catch (e: Exception) {
                baseContext.toast(e.toString())
            }

        }
    }


}