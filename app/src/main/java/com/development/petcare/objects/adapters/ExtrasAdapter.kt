package com.development.petcare.objects.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.R
import com.development.petcare.objects.holders.ExtrasViewHolder

class ExtrasAdapter(private val ListOfExtras: List<String>):RecyclerView.Adapter<ExtrasViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExtrasViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ExtrasViewHolder(layoutInflater.inflate(R.layout.item_extra_service, parent, false))
    }

    override fun getItemCount(): Int = ListOfExtras.size

    override fun onBindViewHolder(holder: ExtrasViewHolder, position: Int) {
        val item = ListOfExtras[position]
        holder.render(item)
    }

}