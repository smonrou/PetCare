package com.development.petcare.objects.holders

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.development.petcare.R

class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var itemPhotoImage: ImageView = view.findViewById(R.id.item_photo_image)

    fun render(photoModel: String) {
        Glide.with(itemPhotoImage.context).load(photoModel).error(R.drawable.panoramica).into(itemPhotoImage)
        Log.i("Fotos", photoModel)
    }
}