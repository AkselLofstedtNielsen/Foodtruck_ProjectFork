package com.example.foodtruck_project

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter class for the Food Truck RecyclerView
class FoodTruckRecycleAdapter(
    val context: Context,
    private val foodtrucks: List<FoodTruck>
) : RecyclerView.Adapter<FoodTruckRecycleAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    // Create and return a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.foodtruck_item, parent, false)
        return ViewHolder(itemView)
    }

    // Bind data to the ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foodTruck = foodtrucks[position]

        // Set the name and open hours of the food truck
        holder.nameView.text = foodTruck.name
        holder.openHoursView.text = foodTruck.openHours

        // Set the food truck image if available
        foodTruck.truckImage?.let { holder.foodTruckImageView.setImageResource(it) }

        val menuText = foodTruck.menu
        val truckName = foodTruck.name

        // Handle click on the menu button
        holder.menuButton.setOnClickListener {
            val intent = Intent(context, FoodTruckMenuActivity::class.java)
            intent.putExtra("menuText", menuText)
            intent.putExtra("truckName", truckName)
            context.startActivity(intent)
        }

        // Handle click on the map button
        holder.mapButton.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            foodTruck.showMe = true
            context.startActivity(intent)
        }
    }

    // Return the number of items in the list
    override fun getItemCount(): Int {
        return foodtrucks.size
    }

    // ViewHolder class for the RecyclerView items
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameView: TextView = itemView.findViewById(R.id.nameTextView)
        var openHoursView: TextView = itemView.findViewById(R.id.openHoursTextView)
        var foodTruckImageView: ImageView = itemView.findViewById(R.id.foodTruckImageView)
        var menuButton: Button = itemView.findViewById(R.id.menuButton)
        var mapButton: Button = itemView.findViewById(R.id.mapButton)
    }
}
