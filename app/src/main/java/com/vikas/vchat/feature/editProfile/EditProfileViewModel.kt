package com.vikas.vchat.feature.editProfile

import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.streamliners.base.BaseViewModel
import com.streamliners.base.ext.execute
import com.streamliners.base.ext.executeOnMain
import com.streamliners.base.taskState.load
import com.streamliners.base.taskState.taskStateOf
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
) : BaseViewModel() {

    val saveProfileTask = taskStateOf<Unit>()
    fun saveUser(
        user: User,
        image: PickedMedia?,
        onSuccess: () -> Unit,
    )
    {
        execute(showLoadingDialog = false){
            saveProfileTask.load {
                val updateUser = user.copy(
                    profileImageUrl = uploadProfileImage(user.email, image)
                )

                userRepo.saveUser(user = updateUser)
                localRepo.onLoggedIn()
                 executeOnMain {  onSuccess() }
            }
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