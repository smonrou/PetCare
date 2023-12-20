package com.development.petcare.objects.holders

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.R
import com.development.petcare.objects.basics.Vet
import com.development.petcare.objects.details.VetDetailsActivity
import com.development.petcare.objects.providers.VetProvider

class VetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val vetName: TextView = view.findViewById(R.id.vet_name)
    private val vetCity: TextView = view.findViewById(R.id.vet_city)
    private val vetType: TextView = view.findViewById(R.id.vet_type)
    private val vetSpeciality: TextView = view.findViewById(R.id.vet_speciality)
    private var vetEmergency: ImageView = view.findViewById(R.id.vet_emergency)
    private val cv_vet: CardView = view.findViewById(R.id.cv_vet)
    var id = ""

    fun render(vetModel: Vet) {
        vetName.text = vetModel.name.toString()
        vetCity.text = vetModel.city.toString()
        vetType.text = vetModel.vetType.toString()
        vetSpeciality.text = vetModel.specialty.toString()
        attendEmergencies(vetModel.attendsEmergencies.toString())
        id = vetModel.id.toString()
        cv_vet.setOnClickListener { toDetails() }
    }

    private fun attendEmergencies(value: String) {
        when (value) {
            "y" -> vetEmergency.setImageResource(R.drawable.ic_emergency)
            "n" -> vetEmergency.setImageResource(R.drawable.ic_emergencyoff)
        }
    }

    private fun toDetails() {
        val intent = Intent(vetName.context, VetDetailsActivity::class.java)
        intent.putExtra("position", VetProvider.VetList[adapterPosition])
        intent.putExtra("id", id)
        vetName.context.startActivity(intent)
    }
}