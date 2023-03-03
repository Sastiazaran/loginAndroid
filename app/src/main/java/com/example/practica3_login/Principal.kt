package com.example.practica3_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase



class Principal : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        auth = Firebase.auth

        val toolbar = findViewById<Toolbar>(R.id.toolbar3)
        setSupportActionBar(toolbar)

        var logout = findViewById<Button>(R.id.logout)

        logout.setOnClickListener(){
            auth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.salir)
        {
            Toast.makeText(this, "opcion salir", Toast.LENGTH_LONG).show()
        }
        else if(item.itemId == R.id.perfil)
        {
            Toast.makeText(this, "opcion perfil", Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }
}