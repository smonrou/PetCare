package com.development.petcare.objects.addobject.loaders

import com.development.petcare.objects.basics.Walker
import com.development.petcare.objects.providers.WalkerProvider.Companion.WalkerList
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class LoadWalkers {

    private val db = FirebaseFirestore.getInstance()
    suspend fun getDataByCityForPetWalker(ciudad: String) {
        val walkerUserRef = db.collection("walkerUser")
        val result = walkerUserRef.whereEqualTo("city", ciudad).get().await()
        for (document in result.documents) {
            val documentName = document.id
            val walker = buildObject(documentName)
            WalkerList.add(walker)
        }
    }

    private suspend fun buildObject(documentName: String): Walker {
        return try {
            val documentSnapshotWalker =
                db.collection("walkerUser").document(documentName).get().await()
            if (documentSnapshotWalker.exists()) {
                val walkerData = documentSnapshotWalker.data
                if (walkerData != null) {
                    val name = walkerData["name"].toString()
                    val city = walkerData["city"].toString()
                    val age = walkerData["age"].toString()
                    val experience = walkerData["experience"].toString()
                    val hourlyPrice = walkerData["hourlyPrice"].toString()
                    val serviceDescription = walkerData["serviceDescription"].toString()
                    val types = walkerData["types"].toString()
                    val userDescription = walkerData["userDescription"].toString()
                    val photoURL = walkerData["photoURL"].toString()

                    Walker(
                        0,
                        name,
                        city,
                        age,
                        types,
                        "3",
                        photoURL,
                        userDescription,
                        serviceDescription,
                        hourlyPrice,
                        experience,
                        schedule = ""
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