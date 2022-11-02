package com.example.foodtruck_project


import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import android.os.Looper
import android.provider.ContactsContract.Profile
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.foodtruck_project.fragments.*
import com.google.android.gms.location.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

    val db = Firebase.firestore
    var auth = Firebase.auth

    class MainActivity : AppCompatActivity() {


    private val REQUEST_LOCATION = 1

    lateinit var navigationMenu: BottomNavigationView
    lateinit var locationProvider : FusedLocationProviderClient
    private val GoogleMapsFragment = GoogleMapsFragment()
    lateinit var locationCallback: LocationCallback
    lateinit var locationRequest: LocationRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationProvider = LocationServices.getFusedLocationProviderClient(this)
        locationRequest = createLocationRequest()
       locationCallback = object : LocationCallback() {
           override fun onLocationResult(locationResult: LocationResult) {
               for (location in locationResult.locations) {
                  // Log.d("!!!","lat: ${location.latitude}, lng: ${location.longitude}")

               }
           }
       }
        //locationProvider.requestLocationUpdates(locationRequest,locationCallback, Looper.getMainLooper())

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION
                )


        } else {
               // Log.d("!!!","before startLocation")
                startLocationUpdates()
        }



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
                    auth = FirebaseAuth.getInstance();

                    if (auth.getCurrentUser() != null) {
                        // Profile user is signed in (getCurrentUser() will be null if not signed in
                        val intent = Intent(this, ProfileActivity::class.java);
                        startActivity(intent);
                    } else {
                        val intent = Intent(this,SignInActivity::class.java)
                        startActivity(intent)
                    }

                }
            }
            true
        }

    }


    fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            locationProvider.requestLocationUpdates(locationRequest,locationCallback, Looper.getMainLooper())
        }

        }


        fun createLocationRequest() =
        LocationRequest.create().apply {
        interval = 2000
        fastestInterval = 1000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }


    /*
     fun addFragment(view : View){
        val foodtruckFragment = FoodTruckFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.foodTruckPopUp, foodtruckFragment, "FoodtruckFragment")
        transaction.commit()
         Log.d("!!!", "hej ${foodtruckFragment}, $transaction")
    }

     */

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startLocationUpdates()
            }
        }


    }

}

