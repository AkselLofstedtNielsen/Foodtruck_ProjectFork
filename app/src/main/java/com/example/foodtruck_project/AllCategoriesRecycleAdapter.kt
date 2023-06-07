package com.example.foodtruck_project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView

class AllCategoriesRecycleAdapter(
    private val context: Context,
    private val categories: List<Category>
) : RecyclerView.Adapter<AllCategoriesRecycleAdapter.ViewHolder>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    // Create and inflate the view for each item in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.category_item, parent, false)
        return ViewHolder(itemView)
    }

    // Bind the data to the views in each item
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category: Category = categories[position]
        holder.categoryButton.setImageResource(category.img)

        // Handle the click event for the category button
        holder.categoryButton.setOnClickListener {
            val intent = Intent(context, FoodTruckListActivity::class.java)
            intent.putExtra("foodtype", category.foodtype)
            context.startActivity(intent)
        }
    }

    // Return the number of items in the RecyclerView
    override fun getItemCount(): Int {
        return categories.size
    }

    // ViewHolder class to hold and manage the views for each item
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryButton: ImageButton = itemView.findViewById(R.id.food_category_imageButton)
    }
}
