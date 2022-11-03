package com.example.foodtruck_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.foodtruck_project.*
import com.example.foodtruck_project.databinding.ActivitySignInBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var navigationMenu : BottomNavigationView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            // användaren signar in (getCurrentUser() kommer att bli null om inte signad in.
            val intent = Intent(this, ProfileActivity::class.java);
            startActivity(intent);
            finish();
        }

        binding = ActivitySignInBinding.inflate(layoutInflater)

        setContentView(binding.root)



        firebaseAuth = FirebaseAuth.getInstance()
        binding.textView2.setOnClickListener {
            val intent = Intent(this, SignUpActivity:: class.java)
            startActivity(intent)
        }

        binding.button22.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()


            if (email.isNotEmpty() && pass.isNotEmpty()) {


                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, ProfileActivity:: class.java)

                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }



            } else {
                Toast.makeText(this, "there are empty fields", Toast.LENGTH_SHORT).show()
            }

        }



        navigationMenu = findViewById(R.id.bottom_navigation3)

        navigationMenu.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.ic_mapexplore -> {
                    val intent = Intent(this, GoogleMapsActivity::class.java)
                    startActivity(intent)
                }
                R.id.ic_searchpref -> {
                    val intent = Intent(this, CategoriesActivity::class.java)
                    startActivity(intent)
                }
                R.id.ic_accountprofile -> {
                    val intent = Intent(this, SignUpActivity:: class.java)
                    startActivity(intent)
                }
            }
            true
        }

    }


}
