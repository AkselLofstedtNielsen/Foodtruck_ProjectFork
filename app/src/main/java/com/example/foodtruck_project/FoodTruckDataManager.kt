package com.example.foodtruck_project

import android.util.Log
import com.google.firebase.firestore.Query
import kotlinx.coroutines.*
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

object FoodTruckDataManager {

    fun getFoodTrucksFromDB(): MutableList<FoodTruck> {
        val foodtrucksFromDB = mutableListOf<FoodTruck>()

        runBlocking {
            val itemsFromDb: List<FoodTruck> = db.collectionGroup("Items")
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
            foodtrucksFromDB.addAll(itemsFromDb)
        }
        return foodtrucksFromDB
    }

    fun searchFoodTrucks(foodType: String): List<FoodTruck> {
        val foodTrucks = getFoodTrucksFromDB()
        if (foodType == "All Food") {
            return foodTrucks
        } else {
            val filteredFoodTrucks = foodTrucks.filter { foodTruck ->
                foodTruck.category == foodType
            }
            return filteredFoodTrucks
        }
    }

}







