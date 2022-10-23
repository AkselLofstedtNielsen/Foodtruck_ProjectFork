package com.example.foodtruck_project

class DataManager {
    fun getAllCategories(): List<Category> {
        //for now hardcoded, should ve got from DB
        val allCategories = mutableListOf<Category>()

        allCategories.add(Category("All Food", R.drawable.all_food_category) )
        allCategories.add(Category("American", R.drawable.american_food_category) )
        allCategories.add(Category("Asian", R.drawable.asian_food_category) )
        allCategories.add(Category("Fast food", R.drawable.fast_food_category) )
        allCategories.add(Category("Indian", R.drawable.indian_food_category) )
        allCategories.add(Category("Italian", R.drawable.italian_food_category) )
        allCategories.add(Category("Mexican", R.drawable.mexican_food_category) )



        return allCategories
    }
}