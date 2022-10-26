package com.example.foodtruck_project

object FoodTruckDataManager {

    val foodtrucks = mutableListOf<FoodTruck>()

    init {
        createMockData()
    }

    private fun createMockData() {


        foodtrucks.add(
            FoodTruck(
                "Raan a haan thai food",
                "11-14",
                "Norrtullsgatan 2",
                category = "Asian"
            )
        )

        foodtrucks.add(
            FoodTruck(
                "Sonora grill",
                "11-20",
                "Tantolundsvägen 72",
                category = "Mexican",
                menuImage = (R.drawable.sonora_menu)
            )
        )
        foodtrucks.add(
            FoodTruck(
                "Bronx",
                "10.30-15",
                "Hälsingegatan 37",
                category = "American",
                menuImage = (R.drawable.bronx_menu)
            )
        )
        foodtrucks.add(
            FoodTruck(
                "The american pie company",
                "11-18",
                "Dalagatan 25",
                category = "American"
            )
        )
        foodtrucks.add(
            FoodTruck(
                "Riksha indian food",
                "11-20",
                "Norra Stationsgatan 86",
                category = "Indian"
            )
        )
        foodtrucks.add(
            FoodTruck(
                "Maharaja",
                "11-16",
                "Tantolundsvägen 70",
                category = "Indian"
            )
        )

        foodtrucks.add(
            FoodTruck(
                "Yalla kebab och falafel",
                "11-22",
                "Ringvögen 162",
                category = "Fast food"
            )
        )
    }
}
