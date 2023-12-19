package com.development.petcare.objects.basics

import android.os.Parcel
import android.os.Parcelable

data class Vet(
    val id: String?,
    var name: String?,
    var address: String?,
    var city: String?,
    var country: String?,
    var schedule: Array<String>?,
    var services: Array<String>?,
    var experience: String?,
    var speciality: String?,
    var photos: Array<String>?,
    var species: String?,
    var refundPolicy: String?,
    var attendsEmergencies: String?,
    var vetType: String?,
    var fees: Array<String>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArray(),
        parcel.createStringArray(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArray(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArray()
    )

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        TODO("Not yet implemented")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vet

        if (id != other.id) return false
        if (name != other.name) return false
        if (address != other.address) return false
        if (city != other.city) return false
        if (country != other.country) return false
        if (schedule != null) {
            if (other.schedule == null) return false
            if (!schedule.contentEquals(other.schedule)) return false
        } else if (other.schedule != null) return false
        if (services != null) {
            if (other.services == null) return false
            if (!services.contentEquals(other.services)) return false
        } else if (other.services != null) return false
        if (experience != other.experience) return false
        if (speciality != other.speciality) return false
        if (photos != null) {
            if (other.photos == null) return false
            if (!photos.contentEquals(other.photos)) return false
        } else if (other.photos != null) return false
        if (species != other.species) return false
        if (refundPolicy != other.refundPolicy) return false
        if (attendsEmergencies != other.attendsEmergencies) return false
        if (vetType != other.vetType) return false
        if (fees != null) {
            if (other.fees == null) return false
            if (!fees.contentEquals(other.fees)) return false
        } else if (other.fees != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (address?.hashCode() ?: 0)
        result = 31 * result + (city?.hashCode() ?: 0)
        result = 31 * result + (country?.hashCode() ?: 0)
        result = 31 * result + (schedule?.contentHashCode() ?: 0)
        result = 31 * result + (services?.contentHashCode() ?: 0)
        result = 31 * result + (experience?.hashCode() ?: 0)
        result = 31 * result + (speciality?.hashCode() ?: 0)
        result = 31 * result + (photos?.contentHashCode() ?: 0)
        result = 31 * result + (species?.hashCode() ?: 0)
        result = 31 * result + (refundPolicy?.hashCode() ?: 0)
        result = 31 * result + (attendsEmergencies?.hashCode() ?: 0)
        result = 31 * result + (vetType?.hashCode() ?: 0)
        result = 31 * result + (fees?.contentHashCode() ?: 0)
        return result
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