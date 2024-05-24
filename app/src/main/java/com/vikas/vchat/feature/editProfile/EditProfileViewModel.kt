package com.vikas.vchat.feature.editProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikas.vchat.data.UserRepo
import com.vikas.vchat.domain.User
import kotlinx.coroutines.launch

class EditProfileViewModel: ViewModel() {
    fun saveUser(user: User, onSuccess: () -> Unit){
        viewModelScope.launch {

            UserRepo().saveUser(user = user)
            onSuccess()
        }

    }
}