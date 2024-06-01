package com.vikas.vchat.data.remote

import android.net.Uri
import androidx.compose.runtime.internal.illegalDecoyCallException
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import kotlinx.coroutines.tasks.await

class StorageRepo {

    suspend fun uploadFile(path: String, uri: Uri):String {

        Firebase.storage.getReference(path).putFile(uri).await()
        val downloadUrl = Firebase.storage.getReference(path).downloadUrl.await()
            ?: throw IllegalStateException("Unable to get download url")
        return downloadUrl.toString()
    }
}