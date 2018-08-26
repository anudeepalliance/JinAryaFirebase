package com.example.work.jinaryafirebase.Classes

import android.net.Uri
import java.util.*
import kotlin.collections.ArrayList

class UserProfile (var userName : String,
                   var userEmail : String,
                   var userGender : String,
                   var dob : Date,
                   var relationshipStatus : String,
                   var colleges : String,
                   var workPlaces : String,
                   var userInterests : String,
                   var currentCity : String,
                   var homeTown : String,
                   var aboutMe : String) {

    constructor(): this("","","", Date(12,12,12),
            "",
            "","", "","","",
            "")

}