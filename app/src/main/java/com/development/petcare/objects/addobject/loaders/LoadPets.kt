package com.development.petcare.objects.addobject.loaders

import com.development.petcare.objects.basics.Pet
import com.development.petcare.objects.providers.PetProvider.Companion.PetList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class LoadPets {
    private val db = FirebaseFirestore.getInstance()
    private val mAuth = FirebaseAuth.getInstance()
    private val userId = mAuth.currentUser?.uid.toString()

    suspend fun obtenerDatosPetsDeUsuario(): List<Pet> {
        return try {
            val documentSnapshotUsuario = db.collection("User").document(userId).get().await()
            if (documentSnapshotUsuario.exists()) {
                val petsArray = documentSnapshotUsuario.get("pets") as? List<*>
                if (!petsArray.isNullOrEmpty()) {
                    val petList = mutableListOf<Pet>()
                    for (documentName in petsArray) {
                        val pet = obtenerDatosPet(documentName)
                        if (pet != null) {
                            petList.add(pet)
                            if (isNotDuplicateName(PetList, pet)) {
                                PetList.add(pet)
                            }
                        }
                    }
                    petList
                } else {
                    emptyList()
                }
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    private suspend fun obtenerDatosPet(documentName: Any?): Pet? {
        return try {
            val documentSnapshotPet =
                db.collection("pet").document(documentName.toString()).get().await()

            if (documentSnapshotPet.exists()) {
                val petData = documentSnapshotPet.data
                if (petData != null) {
                    construirPetDesdeDatos(petData)
                } else {
                    null
                }
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    private fun construirPetDesdeDatos(petData: Map<String, Any>): Pet {
        val name = petData["name"].toString()
        val type = petData["type"].toString()
        val description = petData["description"].toString()
        val photoURL = petData["photoURL"].toString()
        return Pet(name, type, false, false, description, photoURL)
    }

    private fun isNotDuplicateName(petList: MutableList<Pet>, petToCheck: Pet): Boolean {
        for (pet in petList) {
            if (pet.name == petToCheck.name) {
                return false
            }
        }
        return true
    }
}