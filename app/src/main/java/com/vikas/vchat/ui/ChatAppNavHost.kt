package com.vikas.vchat.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vikas.vchat.feature.editProfile.EditProfileScreen
import com.vikas.vchat.feature.home.HomeScreen
import com.vikas.vchat.feature.login.LoginScreen
import com.vikas.vchat.feature.splash.SplashScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChatAppNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    )
    {
        composable(Screen.Splash.route) {
            SplashScreen(koinViewModel(), navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(
                navController,
                koinViewModel()
            )
        }
        composable(
            Screen.EditProfile.format(),
            arguments = listOf(
                navArgument("email"){
                    type = NavType.StringType
                }
            )
        ) {
            val email = it.arguments?.getString("email") ?: error("Email Argument not passed")
            EditProfileScreen(
                navController = navController,
                viewModel = koinViewModel(),
                email = email
            )
        }
        composable(Screen.Home.route){
            HomeScreen()
        }
    }
}