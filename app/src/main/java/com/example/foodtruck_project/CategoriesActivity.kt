package com.example.foodtruck_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoriesActivity : AppCompatActivity() {
    lateinit var dataManager :DataManager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        dataManager  = DataManager()
        val allCategories = dataManager.getAllCategories()

        var recyclerView = findViewById<RecyclerView>(R.id.categoryRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = AllCategoriesRecycleAdapter(this, allCategories)
        recyclerView.adapter = adapter


    }
}