package com.example.work.jinaryafirebase.Classes

import android.widget.ImageView

class PersonList {

    var personListName : String = ""
    var personListCity: String = ""

    constructor() {
        //empty constructor needed
    }

    constructor(personListName : String, personListCity: String) {
        this.personListName = personListName
        this.personListCity = personListCity
    }

}