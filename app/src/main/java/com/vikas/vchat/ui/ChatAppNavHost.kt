package com.vikas.vchat.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vikas.vchat.feature.editProfile.EditProfileScreen
import com.vikas.vchat.feature.login.LoginScreen
import com.vikas.vchat.feature.splash.SplashScreen

@Composable
fun ChatAppNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    )
    {
        composable(Screen.Splash.route) {
            SplashScreen()
        }
        composable(Screen.Login.route) {
            LoginScreen(
                navController
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
                email
            )
        }
    }
}