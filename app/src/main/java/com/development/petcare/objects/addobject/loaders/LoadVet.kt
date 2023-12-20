package com.development.petcare.objects.addobject.loaders

import com.development.petcare.objects.basics.Vet
import com.development.petcare.objects.providers.PhotoProvider.Companion.PhotoList
import com.development.petcare.objects.providers.VetProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


class LoadVet {
    private val db = FirebaseFirestore.getInstance()

    suspend fun getDataByCountryForPetVetList(city: String) {
        val vetUserRef = db.collection("vets")
        val result = vetUserRef.whereEqualTo("city", city).get().await()
        for (document in result.documents) {
            val documentName = document.id
            val vet = buildObject(documentName)
            VetProvider.VetList.add(vet)
        }
    }


    private suspend fun buildObject(documentName: String): Vet {
        return try {
            val documentSnapshotWalker =
                db.collection("vets").document(documentName).get().await()
            if (documentSnapshotWalker.exists()) {
                val vetData = documentSnapshotWalker.data
                if (vetData != null) {
                    val id = documentName
                    val name = vetData["name"].toString()
                    val address = vetData["address"].toString()
                    val city = vetData["city"].toString()
                    val country = vetData["country"].toString()
                    val schedule = vetData["schedule"] as? List<String>
                    val servicesTitles = vetData["servicesTitles"] as? List<String>
                    val servicesDesc = vetData["servicesDesc"] as? List<String>
                    val experience = vetData["experience"].toString()
                    val specialty = vetData["specialty"].toString()
                    val photos = vetData["photos"] as? List<String>
                    val species = vetData["species"].toString()
                    val refundPolicy = vetData["refundPolicy"].toString()
                    val attendsEmergencies = vetData["attendsEmergencies"].toString()
                    val vetType = vetData["vetType"].toString()
                    val fees = vetData["fees"] as? List<String>

                    val vet = Vet(
                        id,
                        name,
                        address,
                        city,
                        country,
                        schedule,
                        servicesTitles,
                        experience,
                        specialty,
                        null,
                        species,
                        refundPolicy,
                        attendsEmergencies,
                        vetType,
                        fees,
                        servicesDesc
                    )
                    photos?.let {
                        for (pic in it) {
                            PhotoList.add(pic)
                        }
                    }
                    vet.photos = photos

                    vet
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