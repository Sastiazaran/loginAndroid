package com.example.practica3_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.os.Bundle as Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        val hacerLogin: Button = findViewById<Button>(R.id.login)

        hacerLogin.setOnClickListener{
            auth.signInWithEmailAndPassword("sastiazaran@hornymail.com", "123456").addOnCompleteListener{ task ->
                if(task.isSuccessful)
                {
                    Toast.makeText(this, "autenticación exitosa", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, Principal::class.java).putExtra("nombre", "Sebastián Asti"))
                }
                else
                {
                    Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    public override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        revisaUsuario(currentUser)
    }

    private fun revisaUsuario(usuario:FirebaseUser?)
    {
        if(usuario == null)
        {
            Toast.makeText(this, "No hay usuarios autenticados", Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(this, "Favor de autenticarse", Toast.LENGTH_SHORT).show()
        }
    }
    
    
}