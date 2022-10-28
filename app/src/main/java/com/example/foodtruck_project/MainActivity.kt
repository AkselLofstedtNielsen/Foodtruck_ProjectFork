package com.example.foodtruck_project


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.foodtruck_project.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private val mapexploreFragment = MapexploreFragment()
    private val searchprefFragment = SearchprefFragment()
    private val AccProfileFragment = AccProfileFragment()
    lateinit var navigationMenu: BottomNavigationView

    private val GoogleMapsFragment = GoogleMapsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationMenu = findViewById(R.id.bottom_navigation)
        replaceFragment(GoogleMapsFragment)

        navigationMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.ic_mapexplore ->
                    replaceFragment(GoogleMapsFragment)

                R.id.ic_searchpref -> {
                    val intent = Intent(this, CategoriesActivity::class.java)
                    startActivity(intent)
                }
                R.id.ic_accountprofile -> {
                    val intent = Intent(this, SignUpActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }

        //försök till att visa foodtruck som ett fragment på kartan TA INTE BORT!!!
/*
        val foodtruckFragment = FoodTruckFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.foodtruckPopup, foodtruckFragment, "FoodtruckFragment")
        transaction.commit()

 */

    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}
