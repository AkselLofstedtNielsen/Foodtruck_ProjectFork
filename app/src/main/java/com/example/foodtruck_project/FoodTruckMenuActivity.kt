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

        val menuText = intent.getStringExtra("menuText")
        if (menuText != null) {
            val menuTextView = findViewById<TextView>(R.id.truckMenuTextView)
            menuTextView.setText(menuText)
        }

        val truckName = intent.getStringExtra("truckName")
        if (truckName != null) {
            val truckTextView = findViewById<TextView>(R.id.truckNameTextView)
            truckTextView.setText(truckName)
        }

        val backButton = findViewById<ImageButton>(R.id.backImageButton)
        backButton.setOnClickListener {
            finish()
        }
    }
}