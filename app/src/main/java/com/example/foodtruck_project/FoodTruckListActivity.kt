package com.example.foodtruck_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FoodTruckListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foodtruck_list)

        var backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        var foodTruckRecyclerView = findViewById<RecyclerView>(R.id.truckRecycleView)

        foodTruckRecyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = FoodTruckRecycleAdapter(this, FoodTruckDataManager.foodtrucks)

        foodTruckRecyclerView.adapter = adapter
    }


}