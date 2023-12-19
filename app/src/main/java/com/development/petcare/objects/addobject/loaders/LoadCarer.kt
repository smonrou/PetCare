package com.development.petcare.objects.addobject.loaders

import com.development.petcare.objects.basics.Carer
import com.development.petcare.objects.providers.CarerProvider.Companion.CarersList
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class LoadCarer {
    private val db = FirebaseFirestore.getInstance()
    suspend fun getDataByCityForCarer(ciudad: String) {
        val walkerUserRef = db.collection("vet")
        val result = walkerUserRef.whereEqualTo("city", ciudad).get().await()
        for (document in result.documents) {
            val documentName = document.id
            val carer = buildObject(documentName)
            CarersList.add(carer)
        }
    }

    private suspend fun buildObject(documentName: String): Carer {
        return try {
            val documentSnapshotWalker =
                db.collection("vet").document(documentName).get().await()
            if (documentSnapshotWalker.exists()) {
                val carerData = documentSnapshotWalker.data
                if (carerData != null) {
                    val id = documentName
                    val name = carerData["institutionalName"].toString()
                    val city = carerData["city"].toString()
                    val country = carerData["country"].toString()
                    val numbWorkers = carerData["numbWorkers"].toString()
                    val profession = carerData["hourlyPrice"].toString()
                    val experience = carerData["experience"].toString()
                    val species = carerData["types"].toString()
                    val uri = carerData["types"].toString()

                    Carer(
                        id,
                        name,
                        city,
                        country,
                        numbWorkers,
                        profession,
                        species,
                        uri
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