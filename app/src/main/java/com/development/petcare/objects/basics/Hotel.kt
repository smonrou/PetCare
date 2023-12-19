package com.development.petcare.objects.basics

import android.os.Parcel
import android.os.Parcelable

data class Hotel(
    val id_hotel: Int,
    val hotel_name: String?,
    val hotel_city: String?,
    var hotel_stars: String?,
    val hotel_description: String?,
    val hotel_prices: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id_hotel)
        parcel.writeString(hotel_name)
        parcel.writeString(hotel_city)
        parcel.writeString(hotel_stars)
        parcel.writeString(hotel_description)
        parcel.writeString(hotel_prices)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Hotel> {
        override fun createFromParcel(parcel: Parcel): Hotel {
            return Hotel(parcel)
        }

        override fun newArray(size: Int): Array<Hotel?> {
            return arrayOfNulls(size)
        }
    }
}