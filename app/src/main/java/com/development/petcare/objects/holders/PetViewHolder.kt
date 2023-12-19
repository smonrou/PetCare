package com.development.petcare.objects.holders

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.development.petcare.objects.details.PetProfileActivity
import com.development.petcare.R
import com.development.petcare.objects.basics.Pet
import com.development.petcare.objects.providers.PetProvider.Companion.PetList

class PetViewHolder(view: View) : ViewHolder(view) {

    val petName = view.findViewById<TextView>(R.id.txt_petName)
    val petAppointements = view.findViewById<TextView>(R.id.txt_pending_appointments)
    val txt_active = view.findViewById<TextView>(R.id.txt_active)
    val pet_type = view.findViewById<ImageView>(R.id.pet_type)
    val cd_pet = view.findViewById<CardView>(R.id.cd_pet)

    @SuppressLint("SetTextI18n")
    fun render(petModel: Pet) {
        petName.text = petModel.name
        if (petModel.appointments) {
            petAppointements.text = "Pending appointments"
        } else petAppointements.text = "No pending appointments"
        if (petModel.active) {
            txt_active.text = "Is receiving a service"
        } else txt_active.text = "Not receiving any type of service"
        Glide.with(pet_type.context).load(petModel.type?.let { petType(it) }).into(pet_type)
        cd_pet.setOnClickListener { toPetProfile() }
    }
    private fun petType(type: String): String {
        return when (type) {
            "Dog" -> "https://cdn-icons-png.flaticon.com/512/851/851925.png"
            "Cat" -> "https://cdn-icons-png.flaticon.com/512/4081/4081943.png"
            "Bird" -> "https://cdn-icons-png.flaticon.com/512/4498/4498547.png"
            "Fish" -> "https://cdn-icons-png.flaticon.com/512/220/220126.png"
            "Other" -> "https://cdn-icons-png.flaticon.com/512/91/91356.png"
            else -> "https://cdn-icons-png.flaticon.com/512/91/91356.png"
        }
    }

    private fun toPetProfile() {
        val context = petName.context
        val pet = PetList[adapterPosition]
        val intent = Intent(context, PetProfileActivity::class.java)
        intent.putExtra("name", pet.name)
        intent.putExtra("type", pet.type)
        intent.putExtra("appointments", pet.appointments)
        intent.putExtra("active", pet.active)
        intent.putExtra("description", pet.description)
        intent.putExtra("uri", pet.uri)
        context.startActivity(intent)
    }

}

