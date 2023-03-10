package com.example.practica3_login

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.Collections.list


class Principal : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    val database = Firebase.database
    val myRef = database.getReference("peliculas")
    lateinit var datos:ArrayList<Peliculas>


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_principal)

            auth = Firebase.auth

            val toolbar = findViewById<Toolbar>(R.id.toolbar3)
            setSupportActionBar(toolbar)

            //lista
            val lista = findViewById<ListView>(R.id.listaPelicula)
            lista.setOnItemClickListener{ adapterView, view, i, l ->
                Toast.makeText(this, datos[i].nombre.toString(), Toast.LENGTH_SHORT).show()
            }

            myRef.addValueEventListener(object: ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    datos = ArrayList<Peliculas>()

                    val value = snapshot.value


                    snapshot.children.forEach { hijo ->
                        println(value)
                        print(hijo.child("nombre").value.toString())
                        var peliculas = Peliculas(hijo.child("nombre").value.toString(), hijo.child("anio").value.toString(),hijo.child("genero").value.toString(),
                            hijo.key.toString())
                            datos.add(peliculas)
                        Log.d(TAG, "Value is: " + value)
                    }
                    llenaLista()
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG, "Failed to read value.", error.toException())
                }

            })

            //var logout = findViewById<Button>(R.id.logout)

            // logout.setOnClickListener(){
            //   auth.signOut()
            // startActivity(Intent(this, MainActivity::class.java))
            //finish()
        }

        private fun llenaLista()
        {
            val adaptador = PeliAdapter(this, datos)
            val lista = findViewById<ListView>(R.id.listaPelicula)
            lista.adapter = adaptador
        }



        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu, menu)
            return super.onCreateOptionsMenu(menu)
        }

        fun logout() {

            auth.signOut()
            startActivity(Intent(this, MainActivity ::class.java))
            finish()

        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            if (item.itemId == R.id.salir) {
                Toast.makeText(this, "opcion salir", Toast.LENGTH_LONG).show()
                logout()
            } else if (item.itemId == R.id.perfil) {
                Toast.makeText(this, "opcion perfil", Toast.LENGTH_LONG).show()
            }
            return super.onOptionsItemSelected(item)
        }
    }
