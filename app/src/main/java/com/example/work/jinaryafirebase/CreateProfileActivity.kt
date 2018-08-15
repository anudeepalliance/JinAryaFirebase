package com.example.work.jinaryafirebase

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.work.jinaryafirebase.Home.HomeActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.create_profile_content.*
import kotlinx.android.synthetic.main.create_profile_app_bar_main.*
import java.util.*
import kotlin.collections.ArrayList
import android.content.Intent
import android.content.Intent.createChooser
import android.net.Uri
import android.provider.MediaStore
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
import com.example.work.jinaryafirebase.CompanionObjects.Companion.WORKPLACE_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.profileInfoSubCollection
import com.example.work.jinaryafirebase.CompanionObjects.Companion.profileInfoSubCollectionDocument
import com.example.work.jinaryafirebase.CompanionObjects.Companion.profilePhotoFolder
import com.example.work.jinaryafirebase.CompanionObjects.Companion.usersCollection
import com.example.work.jinaryafirebase.R.id.view
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import org.jetbrains.anko.*
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import java.io.ByteArrayOutputStream
import android.R.attr.data
import android.app.ProgressDialog
import android.support.annotation.NonNull
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.*



class CreateProfileActivity : AppCompatActivity() {

    val RC_PHOTO_PICKER = 2
    lateinit var usersDocument: DocumentReference
    lateinit var filePath : Uri

    val storage = FirebaseStorage.getInstance()

    var profileImagesFolderRef = storage.reference.
            child(FirebaseAuth.getInstance().currentUser!!.uid).
            child(PROFILE_PHOTO_PATH)


    var insanityCheckPassed : Boolean = false

    private var profilePhotoDownloadString :String = ""
    private var photoSelected = false


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
                toast("File not Found, Please select another Image")
            }
        } else {
            toast("Pick an Image to set a Profile photo")
        }

        when ( requestCode ) {
            RC_PHOTO_PICKER -> {
                filePath = data!!.data
            }
        }
    }

    //TODO get Colleges, Workplace and Interests as separate ArrayList Elements separated by comma

    private fun insanityCheckPassed() {

        if ( name_edit_text.text.isNotBlank() && gender_spinner.selectedItemPosition > 0 &&
                date_picker.date != null && college_edit_text.text.isNotBlank() &&
                workplace_edit_text.text.isNotBlank() && interests_edit_text.text.isNotBlank() &&
                current_city_edit_text.text.isNotBlank() && hometown_edit_text.text.isNotBlank() &&
                about_edit_text.text.isNotBlank() ) {
            insanityCheckPassed = true

        } else {
            insanityCheckPassed = false
            toast("Please Enter all Fields")
        }
    }

    private fun alertIfPhotoNotSelected() {
        if ( !photoSelected ) {
            alert("", "Are you sure you want to continue without setting" +
                    "a profile photo ?") {
                yesButton {
                    toast("Oh…")
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

            val uploadTask = storage.reference.child(FirebaseAuth.getInstance().currentUser!!.uid
            ).child(PROFILE_PHOTO_PATH).putFile(filePath)


            uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    throw task.getException()!!
                }

                // Continue with the task to get the download URL
                profileImagesFolderRef.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val profilePhotoDownloadUri = task.result
                    profilePhotoDownloadString = profilePhotoDownloadUri.toString()

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
                val profileInfo = HashMap<String, Any>()

                profileInfo[NAME_KEY] = name_edit_text.text.toString().trim()
                profileInfo[EMAIL_ID_KEY] = FirebaseAuth.getInstance().currentUser!!.email.toString()
                profileInfo[GENDER_KEY] = name_edit_text.text.toString().trim()
                profileInfo[DOB_KEY] = date_picker.date
                profileInfo[COLLEGE_KEY] =
                        Arrays.asList(college_edit_text.text.toString().trim()) as ArrayList<String>
                profileInfo[WORKPLACE_KEY] =
                        Arrays.asList(workplace_edit_text.text.toString().trim()) as ArrayList<String>
                profileInfo[INTERESTS_KEY] =
                        Arrays.asList(interests_edit_text.text.toString().trim()) as ArrayList<String>
                profileInfo[CURRENT_CITY_KEY] = current_city_edit_text.text.toString().trim()
                profileInfo[HOME_TOWN_KEY] = hometown_edit_text.text.toString().trim()
                profileInfo[ABOUT_ME_KEY] = about_edit_text.text.toString().trim()
                profileInfo[PROFILE_PHOTO_PATH] = profilePhotoDownloadString.trim()


                usersDocument = FirebaseFirestore.getInstance().document(
                        "$usersCollection/${FirebaseAuth.getInstance().
                                currentUser!!.uid}/$profileInfoSubCollectionDocument")

                usersDocument.set(profileInfo).addOnSuccessListener {
                            toast("Profile Saved Successfully")
                            CompanionObjects.profileCreated = true
                            startActivity<HomeActivity>()
                        }.addOnFailureListener {
                            toast("unable to save, please try again later, $it")
                        }

            } catch ( e : Exception) {
                toast(e.toString())
            }
        }
    }

}