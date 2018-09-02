package com.example.work.jinaryafirebase.Classes

import java.sql.Timestamp
import java.util.*

class PersonInsight (var userName : String, var question : String,
                     var personInsightContent : String,
                     var timestamp: Date, var uid : String) {
    constructor(): this("","", "",
            Date(12,12,12,15,15),
            "" )
}