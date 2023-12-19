package com.development.petcare.objects.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.R
import com.development.petcare.objects.basics.Carer
import com.development.petcare.objects.holders.CarerViewHolder

class CarerAdapter(private val ListOfCarers:List<Carer>) : RecyclerView.Adapter<CarerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CarerViewHolder(layoutInflater.inflate(R.layout.activity_carer, parent, false))
    }

    override fun getItemCount(): Int = ListOfCarers.size

    override fun onBindViewHolder(holder: CarerViewHolder, position: Int) {
        val item = ListOfCarers[position]
        holder.render(item)
    }
}