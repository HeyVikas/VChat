package com.vikas.vchat.feature.newChat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.streamliners.base.taskState.comp.whenLoaded
import com.streamliners.compose.android.comp.appBar.TitleBarScaffold
import com.vikas.vchat.ui.comp.UserCard

@Composable
fun NewChatScreen(
    viewModel: NewChatViewModel,
    navController: NavController
) {
    LaunchedEffect(key1 = Unit) {viewModel.start()}
    
    TitleBarScaffold(title = "New Chat",
        navigateUp = {
            navController.navigateUp()
        }) { paddingValues ->

        viewModel.userListTask.whenLoaded {userList ->

            LazyColumn (
                modifier = Modifier.padding(paddingValues),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            )
                {
                    items(userList) {
                        UserCard(user = it)
                    }

            }

        }

    }

}