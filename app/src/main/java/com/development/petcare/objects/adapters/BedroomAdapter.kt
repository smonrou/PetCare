package com.development.petcare.objects.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.development.petcare.R
import com.development.petcare.objects.basics.Bedroom
import com.development.petcare.objects.holders.BedroomViewHolder

class BedroomAdapter(private var ListOfBedrooms: List<Bedroom>) :
    RecyclerView.Adapter<BedroomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BedroomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BedroomViewHolder(layoutInflater.inflate(R.layout.activity_bedroom, parent, false))
    }

    override fun getItemCount(): Int = ListOfBedrooms.size

    override fun onBindViewHolder(holder: BedroomViewHolder, position: Int) {
        val item = ListOfBedrooms[position]
        holder.render(item)
    }
}