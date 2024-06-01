package com.vikas.vchat.helper

import androidx.lifecycle.viewmodel.compose.viewModel
import com.vikas.vchat.data.LocalRepo
import com.vikas.vchat.data.UserRepo
import com.vikas.vchat.data.remote.StorageRepo
import com.vikas.vchat.feature.editProfile.EditProfileViewModel
import com.vikas.vchat.feature.login.LoginViewModel
import com.vikas.vchat.feature.splash.SplashViewModel
import com.vikas.vchat.ui.Screen
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { UserRepo() }
    single { LocalRepo(DataStoreUtil.create(get())) }
    single { StorageRepo() }
}
val viewModelModule = module {
    viewModel { EditProfileViewModel(get(), get(), get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
}