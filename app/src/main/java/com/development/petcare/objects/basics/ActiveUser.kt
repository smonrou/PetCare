package com.development.petcare.objects.basics

import android.os.Parcel
import android.os.Parcelable

data class ActiveUser(
    val name: String?,
    val age: String?,
    val country: String?,
    val city: String?,
    val email: String?,
    val phone: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(age)
        dest.writeString(country)
        dest.writeString(city)
        dest.writeString(email)
        dest.writeString(phone)
    }

    companion object CREATOR : Parcelable.Creator<ActiveUser> {
        override fun createFromParcel(parcel: Parcel): ActiveUser {
            return ActiveUser(parcel)
        }

        override fun newArray(size: Int): Array<ActiveUser?> {
            return arrayOfNulls(size)
        }
    }
}