package com.example.foodtruck_project

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout


lateinit var img1: Image

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var startPage = findViewById<ConstraintLayout>(R.id.startPage)

        //här skapas intentet av kartsidan som jonas ska göra
        //val intent = Intent(this, MapActivity::class.java)

        //ska launcha kartsidan
        startPage.setOnClickListener {
          //  startActivity(intent)
        }

        //Elin kommentar
    }
}

// filip kommentar

//hej cilia
