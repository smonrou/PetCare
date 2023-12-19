package com.development.petcare.objects.basics

import android.os.Parcel
import android.os.Parcelable

data class Pet(
    var name: String?,
    var type: String?,
    var appointments: Boolean,
    var active: Boolean ,
    var description: String?,
    var uri: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(type)
        parcel.writeByte(if (appointments) 1 else 0)
        parcel.writeByte(if (active) 1 else 0)
        parcel.writeString(description)
        parcel.writeString(uri)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pet> {
        override fun createFromParcel(parcel: Parcel): Pet {
            return Pet(parcel)
        }

        override fun newArray(size: Int): Array<Pet?> {
            return arrayOfNulls(size)
        }
    }
}