package com.example.foodtruck_project


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

    val db = Firebase.firestore
    var auth = Firebase.auth

    class MainActivity : AppCompatActivity() {

    lateinit var navigationMenu: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        navigationMenu = findViewById(R.id.bottom_navigation)


        navigationMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.ic_mapexplore -> {
                    val intent = Intent(this, GoogleMapsActivity::class.java)
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






/*    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }*/


    /*
     fun addFragment(view : View){
        val foodtruckFragment = FoodTruckFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.foodTruckPopUp, foodtruckFragment, "FoodtruckFragment")
        transaction.commit()
         Log.d("!!!", "hej ${foodtruckFragment}, $transaction")
    }

     */


}

