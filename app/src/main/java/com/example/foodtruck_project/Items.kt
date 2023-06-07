package com.example.foodtruck_project

import com.google.firebase.firestore.DocumentId
import com.google.type.LatLng

//Data class for the items saved to firestore
data class Items(@DocumentId var DocumentId : String? = null,
                  var UnitID : String? = null,
                  var name: String? = null,
                  var openHours: String? = null,
                  var latitude : Double = 0.0,
                  var longitude: Double = 0.0,
                  var category: String = "",
                  var num: Int? = null,
                  var truck : Boolean? = false,
                  var menu: String? = null
)






