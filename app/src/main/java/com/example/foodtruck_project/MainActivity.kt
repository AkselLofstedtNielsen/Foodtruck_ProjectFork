package com.example.foodtruck_project


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.foodtruck_project.fragments.MapFragment
import com.example.foodtruck_project.fragments.accountFragment
import com.example.foodtruck_project.fragments.searchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView


class MainActivity : AppCompatActivity() {

private val mapFragment = MapFragment()
private val accountFragment = accountFragment()
private val searchFragment = searchFragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(mapFragment)

        NavigationBarView.OnItemReselectedListener {
            when(it.itemId) {
                R.id.ic_map -> replaceFragment(mapFragment)
                R.id.search -> replaceFragment(searchFragment)
                R.id.ic_account -> replaceFragment(accountFragment)
            }

        }

    }


private fun replaceFragment(fragment: Fragment) {
    if (fragment != null) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
        }
    }
}