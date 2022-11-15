package com.example.foodtruck_project

import android.text.method.TextKeyListener.clear
import android.util.Log
import com.google.firebase.database.core.RepoManager.clear
import com.google.firebase.firestore.Query
import kotlinx.coroutines.*
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import java.util.*

object FoodTruckDataManager {

    val foodtrucks = mutableListOf<FoodTruck>()

    init {
       getFoodTrucksFromDB()
    }

    //en funktion som uppdaterar med interval som hämtar funktion som hämtar ner data via firebase
    //så att man kan få uppdatering direkt i recycleviewen för foodtrucklistorna.
     fun main() {
        Timer().scheduleAtFixedRate( object : TimerTask() {
            override fun run () {
                getFoodTrucksFromDB()
            }
        }, 0, 5000)
    }

    init {
        main()
    }


    // funktion som hämtar hem foodtruckprofilernas data från firebase
    //
    private fun getFoodTrucksFromDB() {

        runBlocking {
            val itemsFromDb: List<FoodTruck> = db.collection("users")
                .get()
                .await()
                .documents
                .map { itemDocument ->

                    FoodTruck(
                        name = itemDocument.getString("name") ?: "Food truck name not available",
                        openHours = itemDocument.getString("openHours")
                            ?: "Food truck open hours not available",
                        latitude = itemDocument.getDouble("latitude") ?: 0.0,
                        longitude = itemDocument.getDouble("longitude") ?: 0.0,
                        category = itemDocument.getString("category")
                            ?: "Food truck categoy not available",
                        menu = itemDocument.getString("menu") ?: "Food truck menus not available"
                    )
                }
            foodtrucks.clear()
            foodtrucks.addAll(itemsFromDb)
        }
    }

    fun searchFoodTrucks(foodType: String): List<FoodTruck> {
        val foodTrucks = foodtrucks
        return if (foodType == "All Food") {
            foodTrucks
        } else {
            val filteredFoodTrucks = foodTrucks.filter { foodTruck ->
                foodTruck.category == foodType
            }
            filteredFoodTrucks
        }
    }
}







