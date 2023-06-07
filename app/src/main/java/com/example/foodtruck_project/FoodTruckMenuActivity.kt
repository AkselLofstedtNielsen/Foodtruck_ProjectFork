package com.example.foodtruck_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class FoodTruckMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_truck_menu)

        //Create the textView for the menu
        val menuText = intent.getStringExtra("menuText")
        if (menuText != null) {
            val menuTextView = findViewById<TextView>(R.id.truckMenuTextView)
            menuTextView.setText(menuText)
        }

        //Create the textView for the truckName
        val truckName = intent.getStringExtra("truckName")
        if (truckName != null) {
            val truckTextView = findViewById<TextView>(R.id.truckNameTextView)
            truckTextView.setText(truckName)
        }

        //BackButton
        val backButton = findViewById<ImageButton>(R.id.backImageButton)
        backButton.setOnClickListener {
            finish()
        }
    }
}