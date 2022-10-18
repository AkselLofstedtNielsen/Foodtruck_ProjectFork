package com.example.foodtruck_project



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.foodtruck_project.fragments.AccProfileFragment
import com.example.foodtruck_project.fragments.MapexploreFragment
import com.example.foodtruck_project.fragments.SearchprefFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private val mapexploreFragment = MapexploreFragment()
    private val searchprefFragment = SearchprefFragment()
    private val AccProfileFragment = AccProfileFragment()
    lateinit var navigationMenu : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationMenu = findViewById(R.id.bottom_navigation)

        replaceFragment(mapexploreFragment)

        navigationMenu.setOnItemReselectedListener() {
            when(it.itemId) {
                R.id.ic_mapexplore -> {
                    val intent = Intent(this,MapsActivity::class.java)
                    startActivity(intent)
                }

                R.id.ic_searchpref -> replaceFragment(searchprefFragment)
                R.id.ic_accountprofile -> replaceFragment(AccProfileFragment)
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
