package com.example.work.jinaryafirebase.Classes

import android.net.Uri
import java.util.*
import kotlin.collections.ArrayList

class UserProfile (var name : String, var gender : String, var dob : Date,
                   var colleges : ArrayList<String>,
                   var workPlaces : ArrayList<String>,
                   var Interests : ArrayList<String>,
                   var CurrentCity : String, var HomeTown : String,
                   var aboutMe : String, var profilePicturePath : String?) {

    constructor(): this("","", Date(12,12,12), ArrayList(),ArrayList(),
            ArrayList(),"","","",null)
}