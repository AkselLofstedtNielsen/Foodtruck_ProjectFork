package com.example.foodtruck_project.fragments

import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.foodtruck_project.*
import com.example.foodtruck_project.R
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class GoogleMapsFragment : Fragment() {


    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */


        for (foodtruck in FoodTruckDataManager.foodtrucks) {
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_google_maps, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

    }
}






