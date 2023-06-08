package com.example.foodtruck_project

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodtruck_project.databinding.ActivitySignInBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var navigationMenu: BottomNavigationView
    private lateinit var havingP: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        firebaseAuth = FirebaseAuth.getInstance()

        val user = firebaseAuth.currentUser

        if (user != null) {
            // User is already signed in, redirect to the profile activity
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView2.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.button22.setOnClickListener {
            //Button to sign in existing user
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, ProfileActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "There are empty fields", Toast.LENGTH_SHORT).show()
            }
        }

        navigationMenu = findViewById(R.id.bottom_navigation3)

        navigationMenu.setOnItemSelectedListener { item ->
            //Nav bar
            when (item.itemId) {
                R.id.ic_mapexplore -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.ic_searchpref -> {
                    val intent = Intent(this, CategoriesActivity::class.java)
                    startActivity(intent)
                }
                R.id.ic_accountprofile -> {
                    val intent = Intent(this, SignUpActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }

        havingP = findViewById(R.id.havingP)

        havingP.setOnClickListener {
            //Support button
            val emailIntent = Intent(Intent.ACTION_SENDTO,
                Uri.fromParts("mailto", "tecnicalSupport@email.com", null))
            startActivity(Intent.createChooser(emailIntent, "Send email"))
        }
    }
}
