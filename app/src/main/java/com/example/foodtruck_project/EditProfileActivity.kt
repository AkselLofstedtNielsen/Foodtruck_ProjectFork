package com.example.foodtruck_project

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton

class EditProfileActivity : AppCompatActivity() {

    lateinit var nameEditText: EditText
    lateinit var openHoursEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val foodTruckName = intent.getStringExtra("foodTruckName")
        val openHours = intent.getStringExtra("openHours")

        nameEditText = findViewById(R.id.nameEditText)
        nameEditText.setText(foodTruckName)

        openHoursEditText = findViewById(R.id.openHoursEditText)
        openHoursEditText.setText(openHours)

        val backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener{
            finish()
        }

        val saveButton = findViewById<Button>(R.id.editButton)
        saveButton.setOnClickListener {
            saveChanges()
        }
    }

    private fun saveChanges() {

        val intent = Intent()
        intent.putExtra("foodTruckName", nameEditText.text.toString())
        intent.putExtra("openHours", openHoursEditText.text.toString())
        setResult(Activity.RESULT_OK, intent)

        finish()

        /*
        val intent = Intent(this, ProfileActivity::class.java)

        intent.putExtra("foodTruckName", nameEditText.text)
        intent.putExtra("openHours", openHoursEditText.text)

        getContent.launch(intent)

         */
    }
}