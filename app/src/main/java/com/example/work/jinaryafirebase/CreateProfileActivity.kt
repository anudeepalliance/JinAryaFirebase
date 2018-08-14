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
import com.example.work.jinaryafirebase.CompanionObjects.Companion.PHOTO_PATH
import com.example.work.jinaryafirebase.CompanionObjects.Companion.WORKPLACE_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.profileInfoSubCollection
import com.example.work.jinaryafirebase.CompanionObjects.Companion.profileInfoSubCollectionDocument
import com.example.work.jinaryafirebase.CompanionObjects.Companion.profilePhotoFolder
import com.example.work.jinaryafirebase.CompanionObjects.Companion.usersCollection
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import org.jetbrains.anko.*


class CreateProfileActivity : AppCompatActivity() {

    val RC_PHOTO_PICKER = 2
    lateinit var photoPath : String
    lateinit var usersDocument: DocumentReference

    var storage = FirebaseStorage.getInstance()
    private var storageRef = storage.reference
    var profileFolderRef = storageRef.child(profilePhotoFolder)
    var fileName = "display_photo.jpg"
    var profileImagesFolderRef = profileFolderRef.child(fileName)
    var path = profileImagesFolderRef.path

    var imageFileName = profileImagesFolderRef.name

    var insanityCheckPassed : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_profile_drawer)

        setTitle(R.string.create_profile)
        setSupportActionBar(create_profile_toolbar)

        save_profile_button.setOnClickListener {
            if ( insanityCheckPassed() ) {
                createAndSaveProfile()
                startActivity<HomeActivity>()
                CompanionObjects.profileCreated = true
            }

        }

        profile_photo_image.setOnClickListener {
            profilePhotoSelector()
        }
    }

    private fun profilePhotoSelector() {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/jpeg"
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
            startActivityForResult(Intent.createChooser(intent,
                    "Complete action using"), RC_PHOTO_PICKER)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if ( requestCode == RC_PHOTO_PICKER && resultCode == Activity.RESULT_OK && data != null
        && data.data != null ) {
            val selectedImageUri : Uri = data.data
            val photoRef : StorageReference = MediaStore.Images.Media.getBitmap
                    storageRef.child(selectedImageUri.lastPathSegment)

            photoRef.putFile(selectedImageUri).addOnSuccessListener(this,
                    OnSuccessListener<UploadTask.TaskSnapshot>() {

            })
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    //TODO get Colleges, Workplace and Interests as separate ArrayList Elements separated by comma

    private fun createAndSaveProfile() {

        usersDocument = FirebaseFirestore.getInstance().document(
                "$usersCollection/${FirebaseAuth.getInstance().currentUser!!.uid}")

        try {
            val profileInfo = HashMap<String, Any>()

            profileInfo[NAME_KEY] = name_edit_text.text.toString().trim()
            profileInfo[EMAIL_ID_KEY] = FirebaseAuth.getInstance().currentUser.uid
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
            //TODO change this to add photo URI
            profileInfo[PHOTO_PATH] = photoPath

            usersDocument.collection(profileInfoSubCollection)
                    .document(profileInfoSubCollectionDocument).set(profileInfo)
                    .addOnSuccessListener {
                        toast("Profile Saved Successfully")
                    }.addOnFailureListener {
                        toast("unable to save, please try again later")
                    }

        } catch ( e : Exception) {
            toast(e.toString())
        }


    }

    fun insanityCheckPassed() : Boolean {

        if ( profile_photo_image.image!!.equals(R.drawable.ic_user_icon) ) {
            alert("", "Are you sure you want to continue without setting" +
                    "a profile photo ?") {
                yesButton { toast("Oh…") }
                noButton {  }
            }.show()
        }

        if ( name_edit_text.text.isNotBlank() && gender_spinner.selectedItemPosition > 0 &&
                date_picker.date != null && college_edit_text.text.isNotBlank() &&
                workplace_edit_text.text.isNotBlank() && interests_edit_text.text.isNotBlank() &&
                current_city_edit_text.text.isNotBlank() && hometown_edit_text.text.isNotBlank() &&
                about_edit_text.text.isNotBlank() && photoPath.isNotBlank() ) {

            return true

        } else {

            toast("Please Enter all Fields")
            return false
        }
    }

}
