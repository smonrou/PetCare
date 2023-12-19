package com.development.petcare.objects.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.R
import com.development.petcare.objects.holders.PhotoViewHolder

class PhotoAdapter(private val listOfPhotos:List<String>) : RecyclerView.Adapter<PhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PhotoViewHolder(layoutInflater.inflate(R.layout.item_photo, parent, false))
    }

    override fun getItemCount(): Int = listOfPhotos.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = listOfPhotos[position]
        holder.render(item)
    }
}