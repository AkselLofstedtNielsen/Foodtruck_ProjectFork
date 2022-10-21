package com.example.foodtruck_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView

class FoodTruckMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_truck_menu)


        var menuImage = intent.getIntExtra("menuImage", 0)

        if (menuImage != 0) {

            var menuImageView = findViewById<ImageView>(R.id.menuImageView)
            menuImageView.setImageResource(menuImage)
        }

        var backButton = findViewById<ImageButton>(R.id.backImageButton)
        backButton.setOnClickListener {
            finish()
        }
    }
}