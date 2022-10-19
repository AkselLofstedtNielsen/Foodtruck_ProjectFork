package com.example.foodtruck_project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodTruckRecycleAdapter(val context: Context, val foodtrucks: List<FoodTruck>) :
    RecyclerView.Adapter<FoodTruckRecycleAdapter.ViewHolder>() {

    val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.foodtruck_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foodTruck = foodtrucks[position]
        holder.nameView.text = foodTruck.name
        holder.openHoursView.text = foodTruck.hours

        if (foodTruck.image != null) {
            holder.foodTruckImageView.setImageResource(foodTruck.image!!)
        }
    }

    override fun getItemCount(): Int {
        return foodtrucks.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameView = itemView.findViewById<TextView>(R.id.nameTextView)
        var openHoursView = itemView.findViewById<TextView>(R.id.openHoursTextView)
        var foodTruckImageView = itemView.findViewById<ImageView>(R.id.foodTruckImageView)
    }
}