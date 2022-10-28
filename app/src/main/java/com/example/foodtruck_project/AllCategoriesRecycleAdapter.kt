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
    val context: Context, val categories: List<Category>
) :
    RecyclerView.Adapter<AllCategoriesRecycleAdapter.ViewHolder>() {

    val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.category_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category: Category = categories[position]
        holder.categoryButton.setImageResource(category.img)


        holder.categoryButton.setOnClickListener {
            val intent = Intent(context, FoodTruckListActivity::class.java)
            intent.putExtra("foodtype", category.foodtype)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryButton = itemView.findViewById<ImageButton>(R.id.food_category_imageButton)
    }
}


