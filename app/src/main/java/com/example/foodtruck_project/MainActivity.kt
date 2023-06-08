package com.example.foodtruck_project

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

// Firebase Firestore instance
val db = Firebase.firestore
// Firebase authentication instance
var auth = Firebase.auth

class MainActivity : AppCompatActivity() {

    private val REQUEST_LOCATION = 1

    private lateinit var navigationMenu: BottomNavigationView
    private lateinit var locationProvider: FusedLocationProviderClient
    private val googleMapsFragment = GoogleMapsFragment()
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize location provider and request
        locationProvider = LocationServices.getFusedLocationProviderClient(this)
        locationRequest = createLocationRequest()
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    //Logging the current location of the user
                     Log.d("!!!","lat: ${location.latitude}, lng: ${location.longitude}")
                }
            }
        }

        // Request location permission if not granted
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION
            )
        } else {
            startLocationUpdates()
        }

        // Initialize the navigation menu
        navigationMenu = findViewById(R.id.bottom_navigation)
        replaceFragment(googleMapsFragment)

        // Set the listener for the navigation menu items
        navigationMenu.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.ic_mapexplore -> {
                    replaceFragment(googleMapsFragment)
                }
                R.id.ic_searchpref -> {
                    val intent = Intent(this, CategoriesActivity::class.java)
                    startActivity(intent)
                }
                R.id.ic_accountprofile -> {
                    auth = FirebaseAuth.getInstance()
                    if (auth.currentUser != null) {
                        // User is signed in (getCurrentUser() will be null if not signed in)
                        val intent = Intent(this, ProfileActivity::class.java)
                        startActivity(intent)
                    } else {
                        val intent = Intent(this, SignInActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
            true
        }
    }

    // Start location updates if permission is granted
    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            locationProvider.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        }
    }

    // Create the location request with desired parameters
    private fun createLocationRequest(): LocationRequest {
        return LocationRequest.create().apply {
            interval = 2000
            fastestInterval = 1000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    // Replace the fragment in the container with the provided fragment
    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    // Handle the result of location permission request
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates()
            }
        }
    }
}
