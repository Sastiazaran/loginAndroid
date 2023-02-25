package com.example.practica3_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase



class Principal : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        auth = Firebase.auth

        var logout = findViewById<Button>(R.id.logout)

        logout.setOnClickListener(){
            auth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}