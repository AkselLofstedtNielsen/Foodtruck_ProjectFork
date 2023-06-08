package com.example.foodtruck_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {

    private lateinit var nameView: TextView
    private lateinit var openHoursView: TextView
    private lateinit var latitudeView: TextView
    private lateinit var longitudeView: TextView
    private lateinit var categoryView: TextView
    private lateinit var menuTextView: TextView
    private lateinit var logout: Button

    private lateinit var database: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    private val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val data = result.data
            if (result.resultCode == RESULT_OK && data != null) {
                nameView.text = data.getStringExtra("foodTruckName").toString()
                openHoursView.text = data.getStringExtra("openHours").toString()
                latitudeView.text = data.getStringExtra("latitude").toString()
                longitudeView.text = data.getStringExtra("longitude").toString()
                categoryView.text = data.getStringExtra("category").toString()
                menuTextView.text = data.getStringExtra("menu").toString()

                // Create the item object to be saved in Firestore
                val item = Items(
                    name = nameView.text.toString(),
                    openHours = openHoursView.text.toString(),
                    latitude = latitudeView.text.toString().toDouble(),
                    longitude = longitudeView.text.toString().toDouble(),
                    category = categoryView.text.toString(),
                    menu = menuTextView.text.toString()
                )

                // Save the item in Firestore under the current user's document
                val user = auth.currentUser
                if (user != null) {
                    database.collection("users").document(user.uid).set(item)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d("!!!", "Item added: ${user.uid}")
                            } else {
                                Log.d("!!!", "Failed to add item: ${task.exception}")
                            }
                        }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        database = Firebase.firestore
        auth = Firebase.auth
        val user = auth.currentUser

        nameView = findViewById(R.id.nameView)
        openHoursView = findViewById(R.id.openHoursView)
        longitudeView = findViewById(R.id.longitudeView)
        latitudeView = findViewById(R.id.latitudeView)
        categoryView = findViewById(R.id.categoryView)
        menuTextView = findViewById(R.id.menuTextView)

        if (user != null) {
            // Retrieve the user's document from Firestore and populate the views with the data
            database.collection("users")
                .document(user.uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        nameView.text = document.getString("name")
                        openHoursView.text = document.getString("openHours")
                        latitudeView.text = document.getDouble("latitude").toString()
                        longitudeView.text = document.getDouble("longitude").toString()
                        categoryView.text = document.getString("category")
                        menuTextView.text = document.getString("menu")
                    } else {
                        Log.d("!!!", "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("!!!", "Failed to get document: $exception")
                }
        }

        val editButton = findViewById<Button>(R.id.editButton)
        editButton.setOnClickListener {
            edit()
        }

        val backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        logout = findViewById(R.id.logoutButton)
        logout.setOnClickListener {
            // Sign out the user from Firebase authentication
            Firebase.auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun edit() {
        // Launch the EditProfileActivity with the current profile data
        val intent = Intent(this, EditProfileActivity::class.java)
        intent.putExtra("foodTruckName", nameView.text)
        intent.putExtra("openHours", openHoursView.text)
        intent.putExtra("latitude", latitudeView.text)
        intent.putExtra("longitude", longitudeView.text)
        intent.putExtra("category", categoryView.text)
        intent.putExtra("menu", menuTextView.text)
        getContent.launch(intent)
    }
}
