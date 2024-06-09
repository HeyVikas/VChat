package com.vikas.vchat.temp

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.vikas.vchat.data.remote.FirestoreCollection.userColl
import com.vikas.vchat.domain.Gender
import com.vikas.vchat.domain.User
import kotlinx.coroutines.tasks.await

object Scripts {
    suspend fun saveDummyUser(){

         val userList = listOf(
            User(
                id = "1",
                name = "John Doe",
                email = "john.doe@example.com",
                profileImageUrl = null,
                bio = "Software Engineer from San Francisco.",
                gender = Gender.Male,
                dob = "1990-01-01"
            ),
            User(
                id = "2",
                name = "Jane Smith",
                email = "jane.smith@example.com",
                profileImageUrl = null,
                bio = "Graphic Designer who loves to paint.",
                gender = Gender.Female,
                dob = "1988-05-10"
            ),
            User(
                id = "3",
                name = "Mike Johnson",
                email = "mike.johnson@example.com",
                profileImageUrl = null,
                bio = "Digital Marketer and content creator.",
                gender = Gender.Male,
                dob = "1985-03-20"
            ),
            User(
                id = "4",
                name = "Emily Davis",
                email = "emily.davis@example.com",
                profileImageUrl = null,
                bio = "Passionate about teaching and education.",
                gender = Gender.Female,
                dob = "1992-07-15"
            ),
            User(
                id = "5",
                name = "Chris Brown",
                email = "chris.brown@example.com",
                profileImageUrl = null,
                bio = "Fitness trainer and health enthusiast.",
                gender = Gender.Male,
                dob = "1980-11-25"
            ),
            User(
                id = "6",
                name = "Lisa Wilson",
                email = "lisa.wilson@example.com",
                profileImageUrl = null,
                bio = "Travel blogger and photographer.",
                gender = Gender.Female,
                dob = "1995-04-30"
            ),
            User(
                id = "7",
                name = "David Lee",
                email = "david.lee@example.com",
                profileImageUrl = null,
                bio = "Entrepreneur and tech startup founder.",
                gender = Gender.Male,
                dob = "1982-09-12"
            ),
            User(
                id = "8",
                name = "Sophia Martinez",
                email = "sophia.martinez@example.com",
                profileImageUrl = null,
                bio = "Chef with a passion for gourmet cuisine.",
                gender = Gender.Female,
                dob = "1991-02-18"
            ),
            User(
                id = "9",
                name = "Robert Garcia",
                email = "robert.garcia@example.com",
                profileImageUrl = null,
                bio = "Professional musician and composer.",
                gender = Gender.Male,
                dob = "1987-06-05"
            ),
            User(
                id = "10",
                name = "Mia Hernandez",
                email = "mia.hernandez@example.com",
                profileImageUrl = null,
                bio = "Fashion designer and stylist.",
                gender = Gender.Female,
                dob = "1993-08-22"
            )
        )

        val collRef = Firebase.firestore.userColl()
        val batch = Firebase.firestore.batch()

        userList.forEach { user ->

            batch.set(
                collRef.document(collRef.document().id),
                user
            )

        }
        batch.commit().await()

        }
    }
