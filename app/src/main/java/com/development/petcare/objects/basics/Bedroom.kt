package com.development.petcare.objects.basics

import android.os.Parcel
import android.os.Parcelable

data class Bedroom(
    val id: String?,
    var name: String?,
    var description: String?,
    var extra: String?,
    var photos: List<String>?,
    var price: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readString()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(name)
        dest.writeString(description)
        dest.writeString(extra)
        dest.writeStringList(photos)
        dest.writeString(price)
    }

    companion object CREATOR : Parcelable.Creator<Bedroom> {
        override fun createFromParcel(parcel: Parcel): Bedroom {
            return Bedroom(parcel)
        }

        override fun newArray(size: Int): Array<Bedroom?> {
            return arrayOfNulls(size)
        }
    }
}