package com.development.petcare.objects.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.R
import com.development.petcare.objects.holders.PhotoViewHolder
import com.development.petcare.objects.holders.ServiceViewHolder

class ServiceAdapter(private val listOfServices:List<Array<String>>) : RecyclerView.Adapter<ServiceViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ServiceViewHolder(layoutInflater.inflate(R.layout.item_service, parent, false))
    }

    override fun getItemCount(): Int = listOfServices.size

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val item = listOfServices[position]
        holder.render(item)
    }
}