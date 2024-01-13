package com.development.petcare.objects.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.R
import com.development.petcare.objects.basics.Hotel
import com.development.petcare.objects.holders.HotelViewHolder


class HotelAdapter(private val listOfHotels:List<Hotel>) : RecyclerView.Adapter<HotelViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HotelViewHolder(layoutInflater.inflate(R.layout.activity_hotel, parent, false))
    }

    override fun getItemCount(): Int = listOfHotels.size

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val item = listOfHotels[position]
        holder.render(item)
    }
}