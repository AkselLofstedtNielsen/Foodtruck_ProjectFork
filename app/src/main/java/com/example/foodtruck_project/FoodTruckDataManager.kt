package com.example.foodtruck_project

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import com.google.type.LatLng

object FoodTruckDataManager {

    val foodtrucks = mutableListOf<FoodTruck>()

    init {
        createMockData()
    }

    fun searchFoodTrucks(foodType: String): List<FoodTruck> {
        //sökning bör göras av en DB-fråga
        if (foodType == "All Food")
            return foodtrucks
        else {
            val filteredFoodTrucks = foodtrucks.filter { foodTruck ->
                foodTruck.category == foodType
            }
            return filteredFoodTrucks
        }
    }


    fun createMockData() {

        auth = Firebase.auth

        var name = ""
        var openHours = ""
        var latitude = 0.0
        var longitude = 0.0
        var category = ""

        val user = auth.currentUser

        if (user != null) {

            val docRef = db.collection("users")
                .document(user.uid)
                .collection("Items")
                .orderBy("date", Query.Direction.DESCENDING)
                .limit(1)

            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {

                        val foodtruck = document.toObjects(items::class.java)

                         name = foodtruck[0].name.toString()
                         openHours = foodtruck[0].openHours.toString()
                         latitude = foodtruck[0].latitude
                         longitude = foodtruck[0].longitude
                         category = foodtruck[0].category

                        Log.d("dök", "$name, $openHours, $latitude, $longitude, $category ")

                    } else {
                        Log.d("!!!", "No such document")
                    }
                    //   Log.d("juj", "$name, $openHours, $category")
                }
        }
        foodtrucks.add(FoodTruck(
            name,
            openHours,
            latitude,
            longitude,
            category = category

        ))
        foodtrucks.add(
            FoodTruck(
                "Raan a haan thai food",
                "11-14",
                59.307172185658146,
                18.027411629118248,
                category = "Asian"
            )
        )
        foodtrucks.add(
            FoodTruck(
                "Sonora grill",
                "11-20",
                59.31171691896732,
                18.043566467202066,
                category = "Mexican",
            )
        )
        foodtrucks.add(
            FoodTruck(
                "Bronx",
                "10.30-15",
                59.30742285326887,
                18.029651979181896,
                category = "American",
            )
        )
        foodtrucks.add(
            FoodTruck(
                "The american pie company",
                "11-18",
                59.309138072756866,
                18.02876035836422,
                category = "American"
            )
        )
        foodtrucks.add(
            FoodTruck(
                "Riksha indian food",
                "11-20",
                59.3066057640598,
                18.02948659658339,
                category = "Indian"
            )
        )
        foodtrucks.add(
            FoodTruck(
                "Maharaja",
                "11-16",
                59.311051945233686,
                18.026927098433156,
                category = "Indian"
            )
        )

        foodtrucks.add(
            FoodTruck(
                "Yalla kebab och falafel",
                "11-22",
                59.30618100093423,
                18.03292201418479,
                category = "Fast food"
            )
        )
        foodtrucks.add(
            FoodTruck(
                "Trattoria",
                "11-15",
                59.3079305,
                18.0272796,
                category = "Italian"
            )
        )

        for (foodtruck in foodtrucks) {
            Log.d("död", "${foodtruck.name}, ${foodtruck.openHours}, ${foodtruck.latitude}")
        }

    }
}





