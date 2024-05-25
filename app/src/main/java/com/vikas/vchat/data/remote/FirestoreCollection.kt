package com.vikas.vchat.data.remote

import com.google.firebase.firestore.FirebaseFirestore

object FirestoreCollection {

    fun FirebaseFirestore.userColl() =collection("users")
}