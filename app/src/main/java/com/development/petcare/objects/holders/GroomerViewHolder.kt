package com.development.petcare.objects.holders

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.R
import com.development.petcare.objects.basics.Groomer
import com.development.petcare.objects.details.GroomerDetailsActivity
import com.development.petcare.objects.providers.GroomerProvider

class GroomerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val groomer_name: TextView = view.findViewById(R.id.groomer_name)
    val groomer_NumStars: TextView = view.findViewById(R.id.groomer_NumStars)
    val groomer_city: TextView = view.findViewById(R.id.groomer_city)
    val groomer_price: TextView = view.findViewById(R.id.groomer_price)
    val cv_grooming: CardView = view.findViewById(R.id.cv_grooming)

    var id: String? = "0"
    var uri: String? = ""
    var species: String? = ""
    var description: String? = ""
    var placeDescription: String? = ""
    var serviceDescription: String? = ""
    var schedule: String? = ""
    fun render(groomerModel: Groomer) {
        groomer_name.text = groomerModel.name
        groomer_NumStars.text = groomerModel.numStars
        groomer_city.text = groomerModel.city
        groomer_price.text = groomerModel.priceRange

        id = groomerModel.id
        uri = groomerModel.uri
        species = groomerModel.species
        description = groomerModel.description
        placeDescription = groomerModel.placeDescription
        serviceDescription = groomerModel.serviceDescription
        schedule = groomerModel.schedule
        cv_grooming.setOnClickListener { toGroomerDetails() }
    }

    private fun toGroomerDetails() {
        val context = groomer_name.context
        val groomer = GroomerProvider.GroomerList[adapterPosition]
        val id = groomer.id
        val intent = Intent(context, GroomerDetailsActivity::class.java)
        intent.putExtra("id", id)
        id?.let { Log.i("Saul", "$it GroomerViewHolder") }
        intent.putExtra("name", groomer.name)
        intent.putExtra("city", groomer.city)
        intent.putExtra("species", groomer.species)
        intent.putExtra("description", groomer.description)
        intent.putExtra("priceRange", groomer.priceRange)
        intent.putExtra("placeDescription", groomer.placeDescription)
        intent.putExtra("serviceDescription", groomer.serviceDescription)
        intent.putExtra("schedule", groomer.schedule)
        intent.putExtra("numStars", groomer.numStars)
        intent.putExtra("uri", groomer.uri)
        context.startActivity(intent)
    }
}