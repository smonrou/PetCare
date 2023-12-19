package com.development.petcare.objects.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.R
import com.development.petcare.objects.basics.Pet
import com.development.petcare.objects.holders.PetViewHolder

class PetAdapter(private val listOfPets:List<Pet>) : RecyclerView.Adapter<PetViewHolder>() {
    var counter:Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PetViewHolder(layoutInflater.inflate(R.layout.activity_pet, parent, false))
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val item = listOfPets[position]
        counter = position
        holder.render(item)
    }

    override fun getItemCount(): Int = listOfPets.size

}