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

    var id = 0;
    var description: String? = "";

    fun render(hotelModel: Hotel) {
        hotel_name.text = hotelModel.hotel_name
        hotel_city.text = hotelModel.hotel_city
        hotel_price.text = hotelModel.hotel_prices
        hotel_NumStars.text = hotelModel.hotel_stars
        id = hotelModel.id_hotel
        description = hotelModel.hotel_description
        cv_hotel.setOnClickListener { toHotelDetails() }
    }

    fun toHotelDetails() {
        val context = hotel_name.context
        val hotel = HotelProvider.HotelList[adapterPosition]
        val intent = Intent(context, HotelDetailsActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("hotel_name", hotel.hotel_name)
        intent.putExtra("city", hotel.hotel_city)
        intent.putExtra("stars", hotel.hotel_stars)
        intent.putExtra("description", description)
        intent.putExtra("price", hotel.hotel_prices)
        context.startActivity(intent)
    }
}