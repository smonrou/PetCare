package com.development.petcare.objects.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.development.petcare.R

class ServiceViewHolder(view: View):ViewHolder(view) {
    private var txtService:TextView = view.findViewById(R.id.vd_txtService)
    private var serviceDesc:TextView = view.findViewById(R.id.vd_serviceDesc)

    fun render(serviceHolder: Array<String>){
        txtService.text = serviceHolder[adapterPosition][0].toString()
        serviceDesc.text= serviceHolder[adapterPosition][1].toString()
    }
}