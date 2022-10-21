package com.example.foodtruck_project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.foodtruck_project.databinding.ActivitySignUpBinding
import com.example.foodtruck_project.fragments.GoogleMapsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {




    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var navigationMenu : BottomNavigationView
   // private val GoogleMapsFragment = GoogleMapsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener {
            val intent = Intent(this, SignInActivity:: class.java)

            startActivity(intent)
        }
        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {

                if (pass == confirmPass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, SignInActivity:: class.java)

                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Pasword does not mach", Toast.LENGTH_SHORT).show()
                }


            } else {
                Toast.makeText(this, "there are empty fields", Toast.LENGTH_SHORT).show()
            }

        }


        navigationMenu = findViewById(R.id.bottom_navigation2)

        navigationMenu.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.ic_mapexplore -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.ic_searchpref -> {
                    val intent = Intent(this,CategoriesActivity::class.java)
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
/*    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()
    }*/
}