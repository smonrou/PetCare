package com.development.petcare.objects.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.development.petcare.R
import com.development.petcare.objects.providers.VetProvider.Companion.VetList

class ServiceViewHolder(view: View):ViewHolder(view) {
    private var txtService:TextView = view.findViewById(R.id.vd_txtService)
    private var serviceDesc:TextView = view.findViewById(R.id.vd_serviceDesc)
    var id = ""

    fun render(serviceHolder: String){
        val foundId = VetList.find { it.servicesTitles!!.any { service -> service == serviceHolder } }?.id.toString()
        txtService.text = serviceHolder
        serviceDesc.text = VetList.find { it.id == foundId }?.servicesDesc?.get(adapterPosition)

    }
}