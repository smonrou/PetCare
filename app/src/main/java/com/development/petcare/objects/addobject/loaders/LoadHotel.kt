package com.development.petcare.objects.addobject.loaders

import android.util.Log
import com.development.petcare.objects.basics.Bedroom
import com.development.petcare.objects.basics.Hotel
import com.development.petcare.objects.providers.HotelProvider.Companion.HotelList
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class LoadHotel {
    private val db = FirebaseFirestore.getInstance()

    suspend fun getDataByCountryForHotelList(city: String) {
        val hotelUserRef = db.collection("hotel")
        val result = hotelUserRef.whereEqualTo("city", city).get().await()
        for (document in result.documents) {
            val documentName = document.id
            val hotel = buildObject(documentName)
            HotelList.add(hotel)
        }
    }

    private suspend fun buildObject(documentName: String): Hotel {
        return try {
            val documentSnapshotHotel = db.collection("hotel").document(documentName).get().await()
            if (documentSnapshotHotel.exists()) {
                val hotelData = documentSnapshotHotel.data
                if (hotelData != null) {
                    val name = hotelData["name"].toString()
                    val address = hotelData["address"].toString()
                    val city = hotelData["city"].toString()
                    val country = hotelData["country"].toString()
                    val experience = hotelData["experience"].toString()
                    val species = hotelData["species"].toString()
                    val hotelType = hotelData["hotelType"].toString()
                    val extrasTitles = hotelData["extrasTitles"] as List<String>
                    val extrasDesc = hotelData["extrasDesc"] as List<String>
                    val extrasFees = hotelData["extrasFees"] as List<String>
                    val schedule = hotelData["schedule"] as List<String>
                    val bedroomsStrings = hotelData["bedrooms"] as List<String>
                    val bedrooms = mutableListOf<Bedroom>()
                    if (bedroomsStrings.isNotEmpty()) {
                        for (bed in bedroomsStrings) {
                            val bedroom = buildBedrooms(bed)
                            bedrooms.add(bedroom)
                        }
                    }
                    val hotel = Hotel(
                        documentName,
                        name,
                        address,
                        city,
                        country,
                        experience,
                        species,
                        hotelType,
                        extrasTitles,
                        extrasDesc,
                        extrasFees,
                        schedule,
                        bedrooms
                    )
                    hotel
                } else {
                    throw Exception("The data is null in: $documentName")
                }
            } else {
                throw Exception("Document not Founded: $documentName")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    private suspend fun buildBedrooms(documentName: String): Bedroom {
        return try {
            val bedroomSnapshotBedroom =
                db.collection("Bedrooms").document(documentName).get().await()
            if (bedroomSnapshotBedroom.exists()) {
                val data = bedroomSnapshotBedroom.data
                if (data != null) {
                    val name = data["name"].toString()
                    val description = data["description"].toString()
                    val extra = data["extra"].toString()
                    val photos = data["photos"] as List<String>
                    val price = data["price"].toString()
                    Log.i("fotos", photos[0] + "\n" + photos[1] + "\n" + photos[2])
                    val bedroom = Bedroom(documentName, name, description, extra, photos, price)
                    bedroom
                } else {
                    throw Exception("The data is null in: $documentName")
                }
            } else {
                throw Exception("Document not Founded: $documentName")
            }
        } catch (e: Exception) {
            throw e
        }
    }

}