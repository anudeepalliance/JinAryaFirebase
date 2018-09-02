package com.example.work.jinaryafirebase.Classes

import java.sql.Time
import java.sql.Timestamp
import java.util.*

class Notification(var notificationContent : String,
                   var notificationDate : Date) {

    constructor(): this("",
            Date(12,12,12,45,15) )

}