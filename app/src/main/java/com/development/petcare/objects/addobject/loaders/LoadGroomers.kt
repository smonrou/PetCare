package com.development.petcare.objects.addobject.loaders

import com.development.petcare.objects.basics.Groomer
import com.development.petcare.objects.providers.GroomerProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class LoadGroomers {
    private val db = FirebaseFirestore.getInstance()
    suspend fun getDataByCityForPetStylist(ciudad: String) {
        val stylistUserRef = db.collection("Stylist")
        val result = stylistUserRef.whereEqualTo("city", ciudad).get().await()
        for (document in result.documents) {
            val documentName = document.id
            val stylist = buildObject(documentName)
            GroomerProvider.GroomerList.add(stylist)
        }
    }

    private suspend fun buildObject(documentName: String): Groomer {
        return try {
            val documentSnapshotStylist =
                db.collection("Stylist").document(documentName).get().await()
            if (documentSnapshotStylist.exists()) {
                val stylistData = documentSnapshotStylist.data
                if (stylistData != null) {
                    val name = stylistData["name"].toString()
                    val city = stylistData["city"].toString()
                    val species = stylistData["species"].toString()
                    val description = stylistData["experience"].toString()
                    val priceRange = stylistData["priceRange"].toString()
                    val placeDescription = stylistData["placeDescription"].toString()
                    val serviceDescription = stylistData["serviceDescription"].toString()
                    val schedule = stylistData["schedule"].toString()
                    val photoURL = stylistData["uri"].toString()

                    Groomer(
                        documentName,
                        name,
                        city,
                        species,
                        description,
                        priceRange,
                        placeDescription,
                        serviceDescription,
                        schedule,
                        "3",
                        photoURL
                    )
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