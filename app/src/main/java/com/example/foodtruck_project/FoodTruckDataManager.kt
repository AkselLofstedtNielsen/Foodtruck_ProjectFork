package com.example.foodtruck_project

import android.util.Log
import com.google.firebase.firestore.Query
import kotlinx.coroutines.*
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

object FoodTruckDataManager {

    val foodtrucks = mutableListOf<FoodTruck>()

    private fun getFoodTrucksFromDB(): List<FoodTruck> {

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
            foodtrucks.addAll(itemsFromDb)
        }
        return foodtrucks
    }



    init {
        createMockData()
        getFoodTrucksFromDB()
    }





    fun searchFoodTrucks(foodType: String): List<FoodTruck> {
        val foodTrucks = getFoodTrucksFromDB() // eller  createMockData()
        if (foodType == "All Food") {
            return foodTrucks
        } else {
            val filteredFoodTrucks = foodTrucks.filter { foodTruck ->
                foodTruck.category == foodType
            }
            return filteredFoodTrucks
        }
    }

    private fun createMockData(): List<FoodTruck> {

        foodtrucks.add(
            FoodTruck(
                "Raan a haan thai food",
                "11-14",
                59.307172185658146,
                18.027411629118248,
                category = "Asian",
                menu = "Thai food menu"
            )
        )
        foodtrucks.add(
            FoodTruck(
                "Sonora grill",
                "11-20",
                59.31171691896732,
                18.043566467202066,
                category = "Mexican",
                menu = "Mexican food menu"
            )
        )
        foodtrucks.add(
            FoodTruck(
                "Bronx",
                "10.30-15",
                59.30742285326887,
                18.029651979181896,
                category = "American",
                menu = "American food menu"
            )
        )
        foodtrucks.add(
            FoodTruck(
                "The american pie company",
                "11-18",
                59.309138072756866,
                18.02876035836422,
                category = "American",
                menu = "American food menu2"
            )
        )
        foodtrucks.add(
            FoodTruck(
                "Riksha indian food",
                "11-20",
                59.3066057640598,
                18.02948659658339,
                category = "Indian",
                menu = "Indian food menu"
            )
        )
        foodtrucks.add(
            FoodTruck(
                "Maharaja",
                "11-16",
                59.311051945233686,
                18.026927098433156,
                category = "Indian",
                menu = "Indian food menu2"
            )
        )

        foodtrucks.add(
            FoodTruck(
                "Fast food delux",
                "11-22",
                59.30618100093423,
                18.03292201418479,
                category = "Fast food",
                menu = "Fast food menu"
            )
        )
        foodtrucks.add(
            FoodTruck(
                "Trattoria",
                "11-15",
                59.3079305,
                18.0272796,
                category = "Italian",
                menu = "Italian food menu"
            )
        )
        return foodtrucks
    }


}







