package com.example.foodtruck_project

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

object FoodTruckDataManager {

    private fun getFoodTrucksFromDB(): List<FoodTruck> {
        val foodTrucksFromDB = mutableListOf<FoodTruck>()

        runBlocking { //vi behöver DB-resultaten när vi anropar den här metoden, så vi använder await och hoppar över DB-lyssnare.
            // Annars fylls foodTrucksFromDB efter att aktiviteten dras till användaren
            val itemsFromDb: List<FoodTruck> = db.collectionGroup("Items")
                .get()
                .await()
                .documents
                .map { itemDocument ->
                    //För närvarande validerar vi inte data från DB, default används
                    //TODO should we only show food trucks with complete data?
                    FoodTruck(
                        name = itemDocument.getString("name") ?: "Food truck name not available",
                        hours = itemDocument.getString("openHours") ?: "Food truck open hours not available",
                        latitude = itemDocument.getDouble("latitude") ?: 0.0,
                        longitude = itemDocument.getDouble("longitude") ?: 0.0,
                        category = itemDocument.getString("category") ?: "Food truck categoy not available",
                        menu = itemDocument.getString("menu") ?: "Food truck menus not available",
                        showMe = true
                    )
                }
            foodTrucksFromDB.addAll(itemsFromDb)
        }

        return foodTrucksFromDB
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

        //här gå igenom alla users och hämta ut det första dokumentet, sorterat på tid


/*
        db.collection("Items").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("hallå", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "Error getting documents: ", exception)
            }

 */
        /*
        val docRef =
            db.collection("users")
                .document("c1QQLtE3hiOcUCHYqykIm2V2u313")
                .collection("Items")
                .document("lvZb96dizT7zqirsjWct")


        docRef.get().addOnSuccessListener { document ->
            if (document != null) {

            /*    val food = document.toObject(items::class.java)

                if (food != null) {
                    Log.d("hejsan", "${food.name}")
                }

             */
                val name = document.getString("name")
                val openHours = document.getString("openHours")
                val latitude = document.getDouble("latitude")
                val longitude = document.getDouble("longitude")
                val category = document.getString("category")
                Log.d("!!!", "$name, $openHours, $latitude, $longitude, $category")

                foodtrucks.add(

                    FoodTruck(
                        name.toString(),
                        openHours.toString(),
                        59.3100721082596,
                        18.030037153004116,
                        category = category.toString()

                    )
                )
                Log.d("ekej", "$name")
            } else {
                Log.d("!!!", "No such document")
            }

            //   Log.d("juj", "$name, $openHours, $category")
        }

         */

        val foodtrucks = mutableListOf<FoodTruck>()
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

        for (foodtruck in foodtrucks) {
            Log.d("ded", "${foodtruck.name}, ${foodtruck.hours}, ${foodtruck.latitude}")
        }
        return foodtrucks
    }
}



