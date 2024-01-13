package com.development.petcare.objects.holders

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.R
import com.development.petcare.objects.adapters.PhotoAdapter
import com.development.petcare.objects.basics.Bedroom
import com.development.petcare.objects.providers.BedroomProvider.Companion.BedroomList
import com.development.petcare.objects.providers.HotelProvider.Companion.HotelList

class BedroomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val bedroom_cardView: ConstraintLayout = view.findViewById(R.id.bedroom_cardView)
    val bedroom_name: TextView = view.findViewById(R.id.bedroom_name)
    val bedroom_desc: TextView = view.findViewById(R.id.bedroom_desc)
    val bedroom_extra: TextView = view.findViewById(R.id.bedroom_extra)
    val bedroom_photos: RecyclerView = view.findViewById(R.id.bedroom_photos)
    val bedroom_price: TextView = view.findViewById(R.id.bedroom_price)
    var id:String = ""
    fun render(viewModel: Bedroom) {
        id = viewModel.id.toString()
        bedroom_name.text = viewModel.name.toString()
        bedroom_desc.text = viewModel.description.toString()
        bedroom_extra.text = viewModel.extra.toString()
        bedroom_price.text = viewModel.price.toString()


        val hotel = HotelList.find { it.bedrooms!!.any { bedroom -> bedroom.id == id }}
        val photos = hotel?.bedrooms?.find { bedroom -> bedroom.id == id }?.photos ?: emptyList()
        initRecyclerView(bedroom_cardView.context, photos)
    }

    private fun initRecyclerView(context: Context, photos: List<String>) {
        bedroom_photos.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        bedroom_photos.adapter = PhotoAdapter(photos)
    }

}