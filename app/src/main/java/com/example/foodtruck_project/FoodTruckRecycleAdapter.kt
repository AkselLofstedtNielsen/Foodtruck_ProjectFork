package com.example.foodtruck_project

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.foodtruck_project.fragments.GoogleMapsFragment

class FoodTruckRecycleAdapter(val context: Context, val foodtrucks: List<FoodTruck>) :
    RecyclerView.Adapter<FoodTruckRecycleAdapter.ViewHolder>() {

    val layoutInflater = LayoutInflater.from(context)
    lateinit var GoogleMapsFragment : GoogleMapsFragment

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.foodtruck_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foodTruck = foodtrucks[position]
        holder.nameView.text = foodTruck.name
        holder.openHoursView.text = foodTruck.hours

        if (foodTruck.truckImage != null) {
            holder.foodTruckImageView.setImageResource(foodTruck.truckImage!!)
        }

        var menuImage = foodTruck.menuImage
        holder.menuButton.setOnClickListener {
            val intent = Intent(context, FoodTruckMenuActivity::class.java)
            intent.putExtra("menuImage", menuImage)
            context.startActivity(intent)
        }

        // Vad händer här? Jag kommenterade bort koden, den ger felkod. måste lösas?!
/*        holder.mapButton.setOnClickListener {
            val intent = Intent(context, MapsActivity::class.java)
            context.startActivity(intent)
        }*/

    }

    override fun getItemCount(): Int {
        return foodtrucks.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameView = itemView.findViewById<TextView>(R.id.nameTextView)
        var openHoursView = itemView.findViewById<TextView>(R.id.openHoursTextView)
        var foodTruckImageView = itemView.findViewById<ImageView>(R.id.foodTruckImageView)
        var menuButton = itemView.findViewById<Button>(R.id.menuButton)
        var mapButton = itemView.findViewById<Button>(R.id.mapButton)


    }

}