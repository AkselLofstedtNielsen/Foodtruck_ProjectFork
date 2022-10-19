package com.example.foodtruck_project

object FoodTruckDataManager {

    val foodtrucks = mutableListOf<FoodTruck>()

    init {
        createMockData()
    }

    private fun createMockData() {
        foodtrucks.add(FoodTruck("Sonora grill", "11-20", "Tantolundsvägen 72", null,"mexikanskt"))
        foodtrucks.add(FoodTruck("Bronx", "10.30-15", "Hälsingegatan 37", null, "amerikanskt"))
        foodtrucks.add(FoodTruck("The american pie company", "11-18", "Dalagatan 25", null, "amerikanskt"))
        foodtrucks.add(FoodTruck("Riksha indian food", "11-20", "Norra Stationsgatan 86", null, "indiskt"))
        foodtrucks.add(FoodTruck("Maharaja", "11-16", "Tantolundsvägen 70", null, "indiskt"))
        foodtrucks.add(FoodTruck("Raan a haan thai food", "11-14", "Norrtullsgatan 2", null, "asiatiskt"))
        foodtrucks.add(FoodTruck("Yalla kebab och falafel", "11-22", "Ringvögen 162", null, "snabbmat"))
    }
}