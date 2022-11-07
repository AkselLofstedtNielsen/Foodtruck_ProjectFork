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

class FoodTruckRecycleAdapter(val context: Context, private val foodtrucks: List<FoodTruck>) :
    RecyclerView.Adapter<FoodTruckRecycleAdapter.ViewHolder>() {

    val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.foodtruck_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foodTruck = foodtrucks[position]
        holder.nameView.text = foodTruck.name
        holder.openHoursView.text = foodTruck.openHours

        if (foodTruck.truckImage != null) {
            holder.foodTruckImageView.setImageResource(foodTruck.truckImage!!)
        }

        val menuText = foodTruck.menu
        val truckName = foodTruck.name
        holder.menuButton.setOnClickListener {
            val intent = Intent(context, FoodTruckMenuActivity::class.java)
            intent.putExtra("menuText", menuText)
            intent.putExtra("truckName", truckName)
            context.startActivity(intent)
        }

        holder.mapButton.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            foodTruck.showMe = true
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return foodtrucks.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameView: TextView = itemView.findViewById(R.id.nameTextView)
        var openHoursView: TextView = itemView.findViewById(R.id.openHoursTextView)
        var foodTruckImageView: ImageView = itemView.findViewById(R.id.foodTruckImageView)
        var menuButton: Button = itemView.findViewById(R.id.menuButton)
        var mapButton: Button = itemView.findViewById(R.id.mapButton)
    }

}