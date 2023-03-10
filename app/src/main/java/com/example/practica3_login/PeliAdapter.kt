package com.example.practica3_login

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class PeliAdapter (private val context: Activity, private val arrayList: ArrayList<Peliculas>)
    : ArrayAdapter<Peliculas>(context, R.layout.item, arrayList){

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
        {
            //return super.getView(position, convertView, parent)

            val inflater : LayoutInflater = LayoutInflater.from(context)
            val view : View = inflater.inflate(R.layout.item, null)

            view.findViewById<TextView>(R.id.nombre).text=arrayList[position].nombre
            view.findViewById<TextView>(R.id.anio).text=arrayList[position].anio
            view.findViewById<TextView>(R.id.genero).text=arrayList[position].genero
            return view
        }

}