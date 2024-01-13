package com.development.petcare.objects.holders

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.R
import com.development.petcare.objects.basics.Hotel
import com.development.petcare.objects.providers.HotelProvider
import com.development.petcare.objects.details.HotelDetailsActivity


class HotelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val hotel_name: TextView = view.findViewById(R.id.hotel_name)
    val hotel_city: TextView = view.findViewById(R.id.hotel_city)
    val hotel_NumStars: TextView = view.findViewById(R.id.hotel_NumStars)
    val hotel_price: TextView = view.findViewById(R.id.hotel_price)
    val cv_hotel: CardView = view.findViewById(R.id.cv_hotel)

    var id = ""
    var description = ""

    fun render(hotelModel: Hotel) {
        hotel_name.text = hotelModel.name
        hotel_city.text = hotelModel.city
        hotel_price.text = hotelModel.species
        hotel_NumStars.text = hotelModel.hotelType
        id = hotelModel.id.toString()
        description = hotelModel.experience.toString()
        cv_hotel.setOnClickListener { toHotelDetails() }
    }

    private fun toHotelDetails() {
        val context = hotel_name.context
        val hotel = HotelProvider.HotelList[adapterPosition]
        val intent = Intent(context, HotelDetailsActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("hotel_name", hotel.name)
        context.startActivity(intent)
    }
}