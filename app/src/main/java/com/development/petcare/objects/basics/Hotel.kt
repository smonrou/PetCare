package com.development.petcare.objects.basics

import android.os.Parcel
import android.os.Parcelable

data class Hotel(
    val id: String?,
    val name: String?,
    val address: String?,
    val city: String?,
    val country: String?,
    val experience: String?,
    val species: String?,
    val hotelType: String?,
    val extrasTitles : List<String>?,
    val extrasDesc: List<String>?,
    val extrasFees: List<String>?,
    val schedule: List<String>?,
    val bedrooms: List<Bedroom>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.createTypedArrayList(Bedroom)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(address)
        parcel.writeString(city)
        parcel.writeString(country)
        parcel.writeString(experience)
        parcel.writeString(species)
        parcel.writeString(hotelType)
        parcel.writeStringList(extrasTitles)
        parcel.writeStringList(extrasDesc)
        parcel.writeStringList(extrasFees)
        parcel.writeStringList(schedule)
        parcel.writeTypedList(bedrooms)
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