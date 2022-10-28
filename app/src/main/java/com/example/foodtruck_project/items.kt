package com.example.foodtruck_project

import com.google.firebase.firestore.DocumentId

data class items (@DocumentId var DocumentId : String? = null,
                  var name: String? = null,
                  var openHours: String? = null,
                  var latitude: Double = 0.0,
                  var longitude: Double = 0.0,
                  var num: Int? = null,
                  var truck : Boolean = false)


