package com.development.petcare.objects.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.R
import com.development.petcare.objects.basics.Vet
import com.development.petcare.objects.holders.VetViewHolder

class VetAdapter(private val listOfVets:List<Vet>): RecyclerView.Adapter<VetViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VetViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return VetViewHolder(layoutInflater.inflate(R.layout.activity_vet, parent, false))
    }

    override fun getItemCount(): Int = listOfVets.size

    override fun onBindViewHolder(holder: VetViewHolder, position: Int) {
        val item = listOfVets[position]
        holder.render(item)
    }
}