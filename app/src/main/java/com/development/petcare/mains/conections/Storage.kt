package com.development.petcare.mains.conections

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.UUID

class Storage {
    private val storage = FirebaseStorage.getInstance()
    suspend fun uploadImage(uri: Uri, path: String): Array<String> {
        return withContext(Dispatchers.Main) {
            val uniqueName = "${System.currentTimeMillis()}-${UUID.randomUUID()}"
            val storageRef: StorageReference = storage.reference.child(path + uniqueName)
            try {
                val uploadTask = storageRef.putFile(uri).await()
                val url = storageRef.downloadUrl.await().toString()
                arrayOf(uniqueName, url)
            } catch (e: Exception) {
                arrayOf("", "")
            }
        }
    }
}