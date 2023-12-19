package com.development.petcare.objects.basics

import android.os.Parcel
import android.os.Parcelable

data class Card(
    val cardName: String?,
    val proprietary: String?,
    val cardNumber: String?,
    val cardCode: String?,
    val date: String?,
    val type: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(cardName)
        parcel.writeString(proprietary)
        parcel.writeString(cardNumber)
        parcel.writeString(cardCode)
        parcel.writeString(date)
        parcel.writeString(type)
    }

    companion object CREATOR : Parcelable.Creator<Card> {
        override fun createFromParcel(parcel: Parcel): Card {
            return Card(parcel)
        }

        override fun newArray(size: Int): Array<Card?> {
            return arrayOfNulls(size)
        }
    }
}

