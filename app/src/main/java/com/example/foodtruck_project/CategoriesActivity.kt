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
    lateinit var dataManager: DataManager
    lateinit var navigationMenu: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        dataManager = DataManager()
        val allCategories = dataManager.getAllCategories()

        var recyclerView = findViewById<RecyclerView>(R.id.categoryRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = AllCategoriesRecycleAdapter(this, allCategories)
        recyclerView.adapter = adapter

        navigationMenu = findViewById(R.id.bottom_navigation)

        navigationMenu.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.ic_mapexplore -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.ic_searchpref -> {
                    val intent = Intent(this, CategoriesActivity::class.java)
                    startActivity(intent)
                }
                R.id.ic_accountprofile -> {
                    auth = FirebaseAuth.getInstance();

                    if (auth.getCurrentUser() != null) {
                        // Profile user is signed in (getCurrentUser() will be null if not signed in
                        val intent = Intent(this, ProfileActivity::class.java);
                        startActivity(intent);
                    } else {
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
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()
    }

}
