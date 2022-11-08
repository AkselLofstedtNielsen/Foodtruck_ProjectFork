package com.example.foodtruck_project

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.Instant
import java.time.format.DateTimeFormatter


class ProfileActivity : AppCompatActivity() {

    lateinit var nameView: TextView
    lateinit var openHoursView: TextView
    lateinit var latitudeView: TextView
    lateinit var longitudeView: TextView
    lateinit var categoryView: TextView
    lateinit var menuTextView: TextView

    lateinit var database: FirebaseFirestore
    lateinit var auth: FirebaseAuth
    private lateinit var logout: Button

    private var getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            nameView.text = (result.data?.getStringExtra("foodTruckName")).toString()
            openHoursView.text = (result.data?.getStringExtra("openHours")).toString()
            latitudeView.text = (result.data?.getStringExtra("latitude")).toString()
            longitudeView.text = (result.data?.getStringExtra("longitude")).toString()
            categoryView.text = (result.data?.getStringExtra("category")).toString()
            menuTextView.text = (result.data?.getStringExtra("menu")).toString()

            var item =

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Items(
                        name = nameView.text.toString(),
                        openHours = openHoursView.text.toString(),
                        latitude = (latitudeView.text as String).toDouble(),
                        longitude = (longitudeView.text as String).toDouble(),
                        category = categoryView.text.toString(),
                        date = DateTimeFormatter.ISO_INSTANT.format(Instant.now()),
                        menu = menuTextView.text.toString(),
                    )
                } else {
                    TODO("VERSION.SDK_INT < O")
                }

            val user = auth.currentUser

            if (user != null) {

                database.collection("users").document(user.uid).set(item)
                    .addOnCompleteListener {
                        Log.d("!!!", "add item, ${user}")
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
        menuTextView = findViewById(R.id.menuTextView16)

        if (user == null) {
            nameView.text = ""
            openHoursView.text = ""
            latitudeView.text = ""
            longitudeView.text = ""
            categoryView.text = ""
            menuTextView.text = ""

        } else if (user != null) {

            val docRef = db.collection("users")
                .document(user.uid)
                .collection("users")
                .orderBy("date", Query.Direction.DESCENDING)
                .limit(1)
            Log.d("sås", "$docRef")

            if (docRef == null) {
                nameView.text = ""
                openHoursView.text = ""
                latitudeView.text = ""
                longitudeView.text = ""
                categoryView.text = ""
                menuTextView.text = ""
            } else {

                docRef.get()
                    .addOnSuccessListener { document ->

                        if (document != null) {
                            val item = document.toObjects(Items::class.java)
                            Log.d("sås", "$item")
                            if (item.isNotEmpty()) {

                                val name = item[0].name
                                val openHours = item[0].openHours
                                val latitude = item[0].latitude
                                val longitude = item[0].longitude
                                val category = item[0].category
                                val menu = item[0].menu
                                Log.d(
                                    "profil",
                                    "$name, $openHours, $latitude, $longitude, $category"
                                )

                                nameView.text = name
                                openHoursView.text = openHours
                                latitudeView.text = latitude.toString()
                                longitudeView.text = longitude.toString()
                                categoryView.text = category
                                menuTextView.text = menu
                            }

                        } else {
                            Log.d("!!!", "No such document")

                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d("!!!", "get failed with ", exception)
                    }
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
            Firebase.auth.signOut()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun edit() {

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





