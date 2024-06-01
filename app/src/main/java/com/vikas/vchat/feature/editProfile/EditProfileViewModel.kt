package com.vikas.vchat.feature.editProfile

import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.streamliners.pickers.media.PickedMedia
import com.vikas.vchat.data.LocalRepo
import com.vikas.vchat.data.UserRepo
import com.vikas.vchat.data.remote.StorageRepo
import com.vikas.vchat.domain.User
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditProfileViewModel @Inject constructor(
    private val userRepo: UserRepo,
    private val localRepo: LocalRepo,
    private val storageRepo: StorageRepo
) : ViewModel() {
    fun saveUser(
        user: User,
        image: PickedMedia?,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    )

    {

        val exceptionHandler = CoroutineExceptionHandler { _, error ->
                    error.message ?: "Unknown error"
        }

        viewModelScope.launch (exceptionHandler){

            val updateUser = user.copy(
                profileImageUrl = uploadProfileImage(user.email, image)
            )

            userRepo.saveUser(user = updateUser)
            localRepo.onLoggedIn()
            onSuccess()
        }

    }

    private suspend fun uploadProfileImage(email: String, image: PickedMedia?): String? {
        //TODO: Save images using userID
        // TODO: Use the exact file extension
        return  image?.let {

            storageRepo.uploadFile("profileImages/$email.jpg", it.uri.toUri())
        }

        }
}