package com.example.foodtruck_project

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class CategoriesActivity : AppCompatActivity() {
    private lateinit var dataManager: DataManager
    private lateinit var navigationMenu: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        // Initialize DataManager to retrieve category data
        dataManager = DataManager()
        val allCategories = dataManager.getAllCategories()

        // Set up RecyclerView and its adapter
        val recyclerView = findViewById<RecyclerView>(R.id.categoryRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = AllCategoriesRecycleAdapter(this, allCategories)
        recyclerView.adapter = adapter

        // Set up bottom navigation menu
        navigationMenu = findViewById(R.id.bottom_navigation)
        navigationMenu.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.ic_mapexplore -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.ic_searchpref -> {
                    // Reload the current CategoriesActivity
                    val intent = Intent(this, CategoriesActivity::class.java)
                    startActivity(intent)
                }
                R.id.ic_accountprofile -> {
                    val auth = FirebaseAuth.getInstance()

                    if (auth.currentUser != null) {
                        // User is signed in, open ProfileActivity
                        val intent = Intent(this, ProfileActivity::class.java)
                        startActivity(intent)
                    } else {
                        // User is not signed in, open SignInActivity
                        val intent = Intent(this, SignInActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}
