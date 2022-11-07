package com.example.foodtruck_project

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class FoodTruckWindowAdapter(val context: Context) : GoogleMap.InfoWindowAdapter {

    val layoutInflater = LayoutInflater.from(context)


    override fun getInfoContents(p0: Marker): View? {
        return null
    }

    override fun getInfoWindow(marker: Marker): View? {

        val foodtruckWindow = layoutInflater.inflate(R.layout.foodtruck_item, null)

        val nameView = foodtruckWindow.findViewById<TextView>(R.id.nameTextView)
        val openHoursView = foodtruckWindow.findViewById<TextView>(R.id.openHoursView)
        val menuButton = foodtruckWindow.findViewById<Button>(R.id.menuButton)
        val mapButton = foodtruckWindow.findViewById<Button>(R.id.mapButton)

        val foodtruck = marker.tag as FoodTruck

        nameView.text = foodtruck?.name

        openHoursView.text = foodtruck?.openHours

        /*
        menuButton.setOnClickListener {
            val intent = Intent(context, FoodTruckMenuActivity::class.java)
            intent.putExtra("menuImage", foodtruck.menuImage)
            context.startActivity(intent)
        }

         */
        mapButton.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            foodtruck.showMe = true
            context.startActivity(intent)
        }

        return foodtruckWindow
    }
}