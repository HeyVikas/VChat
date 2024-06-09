package com.vikas.vchat.data

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.vikas.vchat.data.remote.FirestoreCollection.userColl
import com.vikas.vchat.domain.User
import kotlinx.coroutines.tasks.await

class UserRepo {
    suspend fun saveUser(user: User){
            Firebase.firestore
                .userColl()
                .add(user)
                .await()
    }

    suspend fun getAllUser(): List<User>{
        return Firebase.firestore
            .userColl()
            .get()
            .await()
            .toObjects(User::class.java)
    }

            suspend fun  getUserWithEmail(email: String): User?{
                return Firebase.firestore
                    .userColl()
                    .whereEqualTo(User::email.name, email)
                    .get()
                    .await()
                    .toObjects(User::class.java)
                    .firstOrNull()

            }
}