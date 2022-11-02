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


    lateinit var nameView: TextView
    lateinit var openHoursView: TextView
    lateinit var latitudeView: TextView
    lateinit var longitudeView: TextView
    lateinit var categoryView: TextView

    lateinit var database: FirebaseFirestore
    lateinit var auth: FirebaseAuth
    private lateinit var logout : Button


    private var getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            nameView.text = (result.data?.getStringExtra("foodTruckName")).toString()
            openHoursView.text = (result.data?.getStringExtra("openHours")).toString()
            latitudeView.text = (result.data?.getStringExtra("latitude")).toString()
            longitudeView.text = (result.data?.getStringExtra("longitude")).toString()
            categoryView.text = (result.data?.getStringExtra("category")).toString()
            val item = items(
                name = nameView.text.toString(),
                openHours = openHoursView.text.toString(),
                latitude = (latitudeView.text as String).toDouble(),
                longitude = (longitudeView.text as String).toDouble(),
                category = categoryView.text.toString()
            )

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

       // auth = FirebaseAuth.getInstance()

        val user = auth.currentUser
        nameView = findViewById(R.id.nameView)
        openHoursView = findViewById(R.id.openHoursView)
        longitudeView = findViewById(R.id.longitudeView)
        latitudeView = findViewById(R.id.latitudeView)
        categoryView = findViewById(R.id.categoryView)

        //här hämta värden från firebase och sätta in i textfälten ovan
        if(user != null) {

            val docRef = db.collection("users").document(user.uid).collection("Items")
            docRef.addSnapshotListener { snapshot, e ->
                    if (snapshot != null) {
                        for (document in snapshot.documents) {
                            val name = document.getString("name")
                            val openHours = document.getString("openHours")
                            val latitude = document.getDouble("latitude")
                            val longitude = document.getDouble("longitude")
                            val category = document.getString("category")
                            Log.d("profil", "$name, $openHours, $latitude, $longitude, $category")

                            nameView.text = name
                            openHoursView.text = openHours
                            latitudeView.text = latitude.toString()
                            longitudeView.text = longitude.toString()
                            categoryView.text = category
                        }

                    } else {
                        Log.d("!!!", "No such document")
                    }
                }
                //.addOnFailureListener { exception ->
                //    Log.d("!!!", "get failed with ", exception)
                }

/*        if(user != null) {

            val docRef = db.collection("users").document(user.uid).collection("Items").document("Vu7kXqkStbPg543Nca7a")
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val name = document.getString("name")
                        val openHours = document.getString("openHours")
                        val latitude = document.getDouble("latitude")
                        val longitude = document.getDouble("longitude")
                        val category = document.getString("category")
                        Log.d("profil", "$name, $openHours, $latitude, $longitude, $category")

                        nameView.text = name
                        openHoursView.text = openHours
                        latitudeView.text = latitude.toString()
                        longitudeView.text = longitude.toString()
                        categoryView.text = category

                    } else {
                        Log.d("!!!", "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("!!!", "get failed with ", exception)
                }
        }*/



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

            val intent = Intent(this, MainActivity::class.java);
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

        getContent.launch(intent)

    }



}



