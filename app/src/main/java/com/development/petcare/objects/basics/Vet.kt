package com.development.petcare.objects.basics

import android.os.Parcel
import android.os.Parcelable

data class Vet(
    val id: String?,
    var name: String?,
    var address: String?,
    var city: String?,
    var country: String?,
    var schedule: List<String>?,
    var servicesTitles: List<String>?,
    var experience: String?,
    var specialty: String?,
    var photos: List<String>?,
    var species: String?,
    var refundPolicy: String?,
    var attendsEmergencies: String?,
    var vetType: String?,
    var fees: List<String>?,
    var servicesDesc: List<String>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(address)
        parcel.writeString(city)
        parcel.writeString(country)
        parcel.writeStringList(schedule)
        parcel.writeStringList(servicesTitles)
        parcel.writeString(experience)
        parcel.writeString(specialty)
        parcel.writeStringList(photos)
        parcel.writeString(species)
        parcel.writeString(refundPolicy)
        parcel.writeString(attendsEmergencies)
        parcel.writeString(vetType)
        parcel.writeStringList(fees)
        parcel.writeStringList(servicesDesc)
    }

    companion object CREATOR : Parcelable.Creator<Vet> {
        override fun createFromParcel(parcel: Parcel): Vet {
            return Vet(parcel)
        }

        override fun newArray(size: Int): Array<Vet?> {
            return arrayOfNulls(size)
        }
    }


}