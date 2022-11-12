package com.example.foodtruck_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

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

        var selectedCategory: String = intent.getStringExtra("foodtype").toString()
        Log.i("selectedCategory", selectedCategory)

        val foodTrucks : List<FoodTruck> = FoodTruckDataManager.searchFoodTrucks(selectedCategory)

        val adapter = FoodTruckRecycleAdapter(this, foodTrucks)

        foodTruckRecyclerView.adapter = adapter
    }


}