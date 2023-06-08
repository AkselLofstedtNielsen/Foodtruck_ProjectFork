package com.example.foodtruck_project

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class EditProfileActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var openHoursEditText: EditText
    private lateinit var latitudeEditText: EditText
    private lateinit var longitudeEditText: EditText
    private lateinit var categorySpinner: Spinner
    private lateinit var menuEditText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        // Retrieve data passed from the previous activity
        val foodTruckName = intent.getStringExtra("foodTruckName")
        val openHours = intent.getStringExtra("openHours")
        val latitude = intent.getStringExtra("latitude")
        val longitude = intent.getStringExtra("longitude")
        val category = intent.getStringExtra("category")
        val menu = intent.getStringExtra("menu")

        // Initialize views and set their values
        nameEditText = findViewById(R.id.nameEditText)
        nameEditText.setText(foodTruckName)

        openHoursEditText = findViewById(R.id.openHoursEditText)
        openHoursEditText.setText(openHours)

        latitudeEditText = findViewById(R.id.latitudeEditText)
        latitudeEditText.setText(latitude.toString())

        longitudeEditText = findViewById(R.id.longitudeEditText)
        longitudeEditText.setText(longitude.toString())

        menuEditText = findViewById(R.id.menuEditText)
        menuEditText.text = menu

        // Set up back button
        val backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        // Set up save button
        val saveButton = findViewById<Button>(R.id.editButton)
        saveButton.setOnClickListener {
            saveChanges()
        }

        // Set up category spinner
        val arraySpinner = arrayOf(
            "American", "Asian", "Fast food", "Indian", "Italian", "Mexican"
        )

        categorySpinner = findViewById(R.id.categorySpinner)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arraySpinner)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = adapter

        // Set the selected item in the spinner based on the category value
        val spinnerPosition: Int = adapter.getPosition(category)
        categorySpinner.setSelection(spinnerPosition)
    }

    private fun saveChanges() {
        // Send the changes back to the previous activity

        val intent = Intent()
        intent.putExtra("foodTruckName", nameEditText.text.toString())
        intent.putExtra("openHours", openHoursEditText.text.toString())
        intent.putExtra("latitude", latitudeEditText.text.toString())
        intent.putExtra("longitude", longitudeEditText.text.toString())
        intent.putExtra("menu", menuEditText.text.toString())
        intent.putExtra("category", categorySpinner.selectedItem.toString())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
