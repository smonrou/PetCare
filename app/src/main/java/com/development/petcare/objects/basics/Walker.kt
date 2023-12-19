package com.development.petcare.objects.basics

import android.os.Parcel
import android.os.Parcelable


data class Walker(
    val id: Int,
    val name: String?,
    var city: String?,
    var age: String?,
    var species: String?,
    var stars: String?,
    var uri:String?,
    var walkerDescription:String?,
    var serviceDescription:String?,
    var hourPrice:String?,
    var experience:String?,
    var schedule: String?,

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
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
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(city)
        parcel.writeString(age)
        parcel.writeString(species)
        parcel.writeString(stars)
        parcel.writeString(uri)
        parcel.writeString(walkerDescription)
        parcel.writeString(serviceDescription)
        parcel.writeString(hourPrice)
        parcel.writeString(experience)
        parcel.writeString(schedule)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Walker> {
        override fun createFromParcel(parcel: Parcel): Walker {
            return Walker(parcel)
        }

        override fun newArray(size: Int): Array<Walker?> {
            return arrayOfNulls(size)
        }
    }

}