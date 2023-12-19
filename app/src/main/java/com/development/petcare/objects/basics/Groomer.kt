package com.development.petcare.objects.basics

import android.os.Parcel
import android.os.Parcelable

data class Groomer(
    val id: String?,
    var name: String?,
    val city: String?,
    var species: String?,
    var description: String?,
    var priceRange: String?,
    var placeDescription: String?,
    var serviceDescription: String?,
    var schedule: String?,
    var numStars:String?,
    var uri: String?
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
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(city)
        parcel.writeString(species)
        parcel.writeString(description)
        parcel.writeString(priceRange)
        parcel.writeString(placeDescription)
        parcel.writeString(serviceDescription)
        parcel.writeString(schedule)
        parcel.writeString(uri)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Groomer> {
        override fun createFromParcel(parcel: Parcel): Groomer {
            return Groomer(parcel)
        }

        override fun newArray(size: Int): Array<Groomer?> {
            return arrayOfNulls(size)
        }
    }
}