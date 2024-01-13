package com.development.petcare.objects.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.R
import com.development.petcare.objects.providers.HotelProvider.Companion.HotelList

class ExtrasViewHolder(view: View):RecyclerView.ViewHolder(view) {
    val vd_txtExtra:TextView = view.findViewById(R.id.vd_txtExtra)
    val vd_extraDesc:TextView = view.findViewById(R.id.vd_extraDesc)
    val vd_txtExtraPrice:TextView = view.findViewById(R.id.vd_txtExtraPrice)

    fun render(extraModel: String){
        val foundId = HotelList.find { it.extrasTitles!!.any { extra -> extra == extraModel} }?.id.toString()
        vd_txtExtra.text = extraModel
        vd_extraDesc.text = HotelList.find { it.id == foundId }?.extrasDesc?.get(adapterPosition)
        vd_txtExtraPrice.text = HotelList.find { it.id == foundId }?.extrasFees?.get(adapterPosition)
    }
}