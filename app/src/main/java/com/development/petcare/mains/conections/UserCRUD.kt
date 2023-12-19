package com.development.petcare.mains.conections

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class UserCRUD {
    fun createUser(
        age: String,
        name: String,
        nationality: String,
        pass: String,
        phone: String,
        city: String,
        email: String
    ) {
        val mAuth = FirebaseAuth.getInstance()
        val uid = mAuth.currentUser?.uid

        if (uid != null) {
            val db = FirebaseFirestore.getInstance()
            val usuariosCollection = db.collection("User")
            val user = hashMapOf(
                "age" to age,
                "name" to name,
                "nationality" to nationality,
                "password" to pass,
                "phoneNumber" to phone,
                "userCity" to city,
                "user_email" to email
            )
            usuariosCollection.document(uid).set(user)
        }
    }
}