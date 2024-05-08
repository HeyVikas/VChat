package com.vikas.vchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vikas.vchat.feature.editProfile.EditProfileScreen
import com.vikas.vchat.feature.login.LoginScreen
import com.vikas.vchat.feature.splash.SplashScreen
import com.vikas.vchat.ui.ChatAppNavHost
import com.vikas.vchat.ui.Screen
import com.vikas.vchat.ui.theme.VChatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VChatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    ChatAppNavHost()

                    ChatAppNavHost()
                }
            }
        }
    }
}