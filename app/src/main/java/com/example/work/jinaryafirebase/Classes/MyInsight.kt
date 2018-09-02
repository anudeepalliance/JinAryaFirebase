package com.example.work.jinaryafirebase.Classes

import android.net.Uri
import java.sql.Timestamp
import java.util.*

class MyInsight (var myInsightQuestion : String, var myInsightContent : String,
                 var timestamp: Date) {
    constructor(): this("","",
            Date(12,12,12,15,15) )
}