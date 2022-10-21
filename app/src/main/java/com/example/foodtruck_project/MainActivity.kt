package com.example.foodtruck_project



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.foodtruck_project.fragments.AccProfileFragment
import com.example.foodtruck_project.fragments.GoogleMapsFragment
import com.example.foodtruck_project.fragments.MapexploreFragment
import com.example.foodtruck_project.fragments.SearchprefFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private val mapexploreFragment = MapexploreFragment()
    private val searchprefFragment = SearchprefFragment()
    private val AccProfileFragment = AccProfileFragment()
    lateinit var navigationMenu : BottomNavigationView

    private val GoogleMapsFragment = GoogleMapsFragment()

    lateinit var button678 : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button678 = findViewById(R.id.button678)


        button678.setOnClickListener {
            val intent = Intent(this, SignUpActivity:: class.java)
            startActivity(intent)
        }


        navigationMenu = findViewById(R.id.bottom_navigation)

        //replaceFragment(mapexploreFragment)
        replaceFragment(GoogleMapsFragment)

        navigationMenu.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.ic_mapexplore ->
                    replaceFragment(GoogleMapsFragment)

                R.id.ic_searchpref -> {
                    val intent = Intent(this,CategoriesActivity::class.java)
                    startActivity(intent)
                }
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
