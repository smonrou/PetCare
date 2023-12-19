package com.development.petcare.objects.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.R
import com.development.petcare.objects.basics.Card
import com.development.petcare.objects.holders.CardViewHolder


class CardAdapter(private val ListOfTagets:List<Card>): RecyclerView.Adapter<CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CardViewHolder(layoutInflater.inflate(R.layout.activity_card, parent, false))
    }

    override fun getItemCount(): Int = ListOfTagets.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = ListOfTagets[position]
        holder.render(item)
    }

}