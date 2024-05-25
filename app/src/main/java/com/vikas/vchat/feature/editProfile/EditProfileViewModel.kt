package com.vikas.vchat.feature.editProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikas.vchat.data.LocalRepo
import com.vikas.vchat.data.UserRepo
import com.vikas.vchat.domain.User
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditProfileViewModel @Inject constructor(
    private val userRepo: UserRepo,
    private val localRepo: LocalRepo
) : ViewModel() {
    fun saveUser(user: User, onSuccess: () -> Unit){
        viewModelScope.launch {
            UserRepo().saveUser(user = user)
            userRepo.saveUser(user = user)
            localRepo.onLoggedIn()
            onSuccess()
        }

    }
}