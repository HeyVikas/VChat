package com.vikas.vchat.data

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.vikas.vchat.domain.User
import kotlinx.coroutines.tasks.await

class UserRepo {
    suspend fun saveUser(user: User){
            Firebase.firestore
                .collection("users")
                .add(user)
                .await()
    }

}