package com.example.foodtruck_project

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Item : AppCompatActivity() {


    lateinit var nameView : EditText
    lateinit var database : FirebaseFirestore
    lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)


        database = Firebase.firestore
        auth = Firebase.auth


        nameView = findViewById(R.id.nameView)

        val button2 = findViewById<Button>(R.id.button678)

        button2.setOnClickListener {
            saveItem()
        }


    }

    fun saveItem() {
        val item = items(name = nameView.text.toString())
        nameView.setText("")


        val user = auth.currentUser

        if (user == null) {
            return
        }

        database.collection("users").document(user.uid).collection("Items").add(item)
            .addOnCompleteListener {
                Log.d("!!!", "add item")
            }
    }


}