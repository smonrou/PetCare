package com.development.petcare.objects.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.R
import com.development.petcare.objects.basics.Groomer
import com.development.petcare.objects.holders.GroomerViewHolder

class GroomerAdapter(private val ListOfGroomers:List<Groomer>) : RecyclerView.Adapter<GroomerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroomerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GroomerViewHolder(layoutInflater.inflate(R.layout.activity_groomer, parent, false))
    }

    override fun getItemCount(): Int = ListOfGroomers.size

    override fun onBindViewHolder(holder: GroomerViewHolder, position: Int) {
        val item = ListOfGroomers[position]
        holder.render(item)
    }
}