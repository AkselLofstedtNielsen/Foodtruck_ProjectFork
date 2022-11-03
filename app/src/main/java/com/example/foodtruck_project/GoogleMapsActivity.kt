package com.example.foodtruck_project

import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.foodtruck_project.FoodTruckDataManager
import com.example.foodtruck_project.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.foodtruck_project.databinding.ActivityGoogleMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class GoogleMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityGoogleMapsBinding
    private var myPosition : LatLng = LatLng(59.0,18.0)
    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val permissionCode = 101


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGoogleMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        getCurrentLocation()
    }


    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION
            ) !=
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), permissionCode
            )
            return
        }
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {

                location ->

            if (location != null) {

                currentLocation = location

                Toast.makeText(
                    applicationContext, currentLocation.latitude.toString() + "" +
                            currentLocation.longitude.toString(), Toast.LENGTH_LONG
                ).show()

                val mapFragment = supportFragmentManager
                    .findFragmentById(R.id.map) as SupportMapFragment
                mapFragment.getMapAsync(this)
            }

        }


    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {

            permissionCode -> if (grantResults.isNotEmpty() && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED
            ) {

                getCurrentLocation()
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {


    val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)
    val markerOptions = MarkerOptions().position(latLng).title("Current Location")

    googleMap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15f))
        googleMap?.addMarker(markerOptions)


    /*  for (foodtruck in FoodTruckDataManager.foodtrucks) {
            val currentFoodTruck = LatLng(foodtruck.latitude, foodtruck.longitude)
            googleMap.addMarker(
                MarkerOptions().position(currentFoodTruck).title("${foodtruck.name}")
            )
        }

        val currentLocation = LatLng(59.31118, 18.03002)
        googleMap.addMarker(MarkerOptions().position(currentLocation).title("Din plats"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f))

        for (foodtruck in FoodTruckDataManager.foodtrucks) {

            if (foodtruck.showMe) {
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(foodtruck.latitude, foodtruck.longitude), 17f))
                foodtruck.showMe = false
            }
        }



        googleMap.setOnMarkerClickListener { // Här ska man visa upp foodtrucken vars pin är tryckt på
            //atm läggs bara en pin till för att visa på funktion


            //    (activity as MainActivity?)?.addFragment()

            val yourLocation = LatLng(59.31037749894223, 18.025368727268727)
            googleMap.addMarker(MarkerOptions().position(yourLocation).title("Du klickade på en marker"))
            false
        }
    }
*/

    }
}