package com.example.foodtruck_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ProfileActivity : AppCompatActivity() {


    lateinit var nameView : TextView
    lateinit var openHoursView : TextView
    lateinit var database : FirebaseFirestore
    lateinit var auth : FirebaseAuth

    private var getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            nameView.text = (result.data?.getStringExtra("foodTruckName")).toString()
            openHoursView.text = (result.data?.getStringExtra("openHours")).toString()
            val item = items(name = nameView.text.toString(), openHours = openHoursView.text.toString())
            val user = auth.currentUser

            if (user != null) {
                database.collection("users").document(user.uid).collection("Items").add(item)
                    .addOnCompleteListener {
                        Log.d("!!!", "add item")
                    }
            }
        }


            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_profile)


                database = Firebase.firestore
                auth = Firebase.auth


                nameView = findViewById(R.id.nameView)
                openHoursView = findViewById(R.id.openHoursView)

                val editButton = findViewById<Button>(R.id.editButton)

                editButton.setOnClickListener {
                    edit()
                }

                val backButton = findViewById<ImageButton>(R.id.backButton)
                backButton.setOnClickListener {
                    finish()
                }
            }

            fun edit() {

                val intent = Intent(this, EditProfileActivity::class.java)
                var foodTruckName = nameView.text
                intent.putExtra("foodTruckName", foodTruckName)
                var openHours = openHoursView.text
                intent.putExtra("openHours", openHours)
                getContent.launch(intent)

            }

        }



