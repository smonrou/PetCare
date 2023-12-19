package com.development.petcare.objects.holders

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.development.petcare.R

class PhotoViewHolder(view: View) : ViewHolder(view) {
    private var itemPhotoImage:ImageView = view.findViewById(R.id.item_photo_image)

    fun render(photoModel: String) {
        Glide.with(itemPhotoImage.context).load(photoModel).into(itemPhotoImage)
    }
}