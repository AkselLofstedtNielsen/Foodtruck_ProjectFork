package com.example.foodtruck_project

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class GoogleMapsFragment : Fragment() {

    // Callback to be invoked when the map is ready
    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
        googleMap.isMyLocationEnabled = true

        // Add markers for each food truck in the data manager
        for (foodtruck in FoodTruckDataManager.foodtrucks) {
            val currentFoodTruck = LatLng(foodtruck.latitude, foodtruck.longitude)
            googleMap.addMarker(
                MarkerOptions().position(currentFoodTruck).title("${foodtruck.name}")
            )
        }

        val currentLocation = LatLng(59.31118, 18.03002)
        googleMap.addMarker(MarkerOptions().position(currentLocation).title("Din plats"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 12f))

        // Move the camera to the location of each food truck that needs to be shown
        for (foodtruck in FoodTruckDataManager.foodtrucks) {
            if (foodtruck.showMe) {
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(foodtruck.latitude, foodtruck.longitude), 15f))
                foodtruck.showMe = false
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_google_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}
