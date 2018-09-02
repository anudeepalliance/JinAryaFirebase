package com.example.work.jinaryafirebase.Classes

import android.net.Uri
import java.util.*

class SuggestedPeople (var userName : String,
                       var uid : String, var mutualConnections : Int) {

    constructor(): this("","", 0)
}