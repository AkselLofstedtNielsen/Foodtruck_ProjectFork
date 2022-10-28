package com.example.foodtruck_project

object FoodTruckDataManager {

    val foodtrucks = mutableListOf<FoodTruck>()

    init {
        createMockData()
    }

    fun searchFoodTrucks(foodType:String) : List<FoodTruck> {
        //sökning bör göras av en DB-fråga
        val filteredFoodTrucks = foodtrucks.filter { foodTruck -> foodTruck.category == foodType  }

        return filteredFoodTrucks
    }

    private fun createMockData() {


        foodtrucks.add(
            FoodTruck("Raan a haan thai food",
                "11-14",
                59.307172185658146,
                18.027411629118248,
                category = "Asian")
        )

        foodtrucks.add(
            FoodTruck(
                "Sonora grill",
                "11-20",
                59.31171691896732,
                18.043566467202066,
                category = "Mexican",
                menuImage = (R.drawable.sonora_menu)
            )
        )
        foodtrucks.add(
            FoodTruck(
                "Bronx",
                "10.30-15",
                59.30742285326887,
                18.029651979181896,
                category = "American",
                menuImage = (R.drawable.bronx_menu)
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
    }
}
