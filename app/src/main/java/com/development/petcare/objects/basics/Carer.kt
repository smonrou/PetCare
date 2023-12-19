package com.development.petcare.objects.basics

import android.os.Parcel
import android.os.Parcelable

data class Carer(
    val id:String?,
    var name: String?,
    var city: String?,
    var country: String?,
    var numbWorkers: String?,
    var profession: String?,
    var species: String?,
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
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(city)
        parcel.writeString(country)
        parcel.writeString(numbWorkers)
        parcel.writeString(profession)
        parcel.writeString(species)
        parcel.writeString(uri)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Carer> {
        override fun createFromParcel(parcel: Parcel): Carer {
            return Carer(parcel)
        }

        override fun newArray(size: Int): Array<Carer?> {
            return arrayOfNulls(size)
        }
    }
}
