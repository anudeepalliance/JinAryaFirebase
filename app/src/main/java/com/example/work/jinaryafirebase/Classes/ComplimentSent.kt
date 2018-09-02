package com.example.work.jinaryafirebase.Classes

import java.sql.Timestamp
import java.util.*

class ComplimentSent (var userName : String, var uid : String,
                      var timestamp : Date,
                      var complimentSentContent : String ) {
    constructor(): this("","",
            Date(12,12,12,15,15),
            "" )
}