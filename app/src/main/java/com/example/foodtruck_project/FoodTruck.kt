package com.example.foodtruck_project

import com.google.type.LatLng

data class FoodTruck(

    var name: String,
    var openHours: String,
    var latitude: Double,
    var longitude: Double,
    var category: String,
    var menu: String? = null,
    var showMe: Boolean = false
) {

}