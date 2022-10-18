package com.example.foodtruck_project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AllCategoriesRecycleAdapter(val context : Context, val categories : List<Category>) :
        RecyclerView.Adapter<AllCategoriesRecycleAdapter.ViewHolder>(){

    val layoutInflater = LayoutInflater.from(context) //"Uppblåsaren" som skapar objekten utifrån layouten


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Jag vill skapa view utifrån min layout den som jag kallade category_item
        val itemView = layoutInflater.inflate(R.layout.category_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val category : Category = categories[position]
        holder.categoryButton.setImageResource(category.img)

    }

    override fun getItemCount(): Int {
        return categories.size
        //returnera så många man har i sin lista. detta kan även skrivas
        // såhär i kotlin:
        //override fun getItemCount() = students.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) { //egen klass som
        // tar in en view och ärver av RecyclerView.ViewHolder(itemView)

        //i vår viewHolderklass, den behöver hålla reda på våra olika element: textViews mm:
        val categoryButton = itemView.findViewById<ImageButton>(R.id.food_category_imageButton)

    }

}