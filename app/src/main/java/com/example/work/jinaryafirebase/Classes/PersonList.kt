package com.example.work.jinaryafirebase.Classes

class PersonList {
    var userId : String = ""
    var userName : String = ""
    var currentCity: String = ""

    constructor() {
        //empty constructor needed
    }

    constructor(userId: String, personListName : String, personListCity: String) {
        this.userId = userId
        this.userName = personListName
        this.currentCity = personListCity
    }

}