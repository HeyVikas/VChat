package com.vikas.vchat.ui

sealed class Screen(
    val route: String
) {
    data object Splash : Screen("Splash")
    data object Login : Screen("Login")

    data object Home : Screen("Home")

    class EditProfile(
        email: String
    ) : Screen("EditProfile?email=$email"){
        companion object{
            fun format() = "EditProfile?email={email}"
        }
    }

}