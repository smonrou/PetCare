package com.development.petcare.objects.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.R
import com.development.petcare.objects.basics.Walker
import com.development.petcare.objects.holders.WalkerViewHolder

class WalkerAdapter(private val ListOfWalkers: List<Walker>) : RecyclerView.Adapter<WalkerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalkerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return WalkerViewHolder(layoutInflater.inflate(R.layout.activity_walker, parent, false))
    }

    override fun getItemCount(): Int = ListOfWalkers.size


    override fun onBindViewHolder(holder: WalkerViewHolder, position: Int) {
        val item = ListOfWalkers[position]
        holder.render(item)
    }

}