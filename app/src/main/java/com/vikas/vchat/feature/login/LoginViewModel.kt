package com.vikas.vchat.feature.login

import androidx.collection.emptyLongSet
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import com.vikas.vchat.data.LocalRepo
import com.vikas.vchat.data.UserRepo
import com.vikas.vchat.helper.viewModelModule
import com.vikas.vchat.ui.Screen
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val userRepo: UserRepo,
    private val localRepo: LocalRepo
): ViewModel() {

    fun onLoggedIn(email: String, navController: NavHostController) {
        viewModelScope.launch {
            val user = userRepo.getUserWithEmail(email)
            if (user != null) {
                // Save in local & navigate to home screen
                localRepo.onLoggedIn(user)
                navController.navigate(Screen.Home.route)
            }
            else{
                // Navigate to EditProfile Screen
                navController.navigate(Screen.EditProfile(email).route)
            }

        }
    }
}