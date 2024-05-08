package com.vikas.vchat.feature.editProfile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    email: String
) {

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Profile") }
            )
    },

        snackbarHost = {
            //TODO : SanckBar not visible keyboard not open

            SnackbarHost (hostState = snackbarHostState,
                modifier = Modifier.fillMaxWidth())
        }

    ){ paddingValues ->

        var name by remember { mutableStateOf("") }
        var bio by remember { mutableStateOf("") }
        var nameError by remember { mutableStateOf(false) }
        var bioError by remember { mutableStateOf(false) }

        Column (
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
                ){
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = {
                    it -> name = it
                    nameError = it.isBlank() },
                label = { Text(text = "Name")} ,
                isError = nameError,
                supportingText = if (nameError) {
                    { Text(text = "Required!") }
                }
                else null
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { },
                readOnly = true,
                label = { Text(text = "Email")},
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = bio,
                onValueChange = {
                    it -> bio = it
                    bioError = it.isBlank() },
                label = { Text(text = "Bio")} ,
                        isError = bioError,
                supportingText = if (bioError) {
                    { Text(text = "Required!") }
                }
                else null
            )
            val scope = rememberCoroutineScope()

            Button(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    if (name.isBlank() && bio.isBlank()){
                        nameError = true
                        bioError = true
                        scope.launch {
                            snackbarHostState.showSnackbar("Please input your name and bio")
                        }
                    }


                    if (name.isBlank()){
                        nameError = true
                        scope.launch {
                            snackbarHostState.showSnackbar("Please input your name")
                        }
                        return@Button
                    }

                    if (bio.isBlank()){
                        bioError = true
                        scope.launch {
                            snackbarHostState.showSnackbar("Please input your bio")
                        }
                    }

                    if(name.isNotBlank() && bio.isNotBlank()){
                    scope.launch {
                        snackbarHostState.showSnackbar("Your name is $name, and bio is $bio")
                    }
                }

                }
            ) {
                Text(text = "Save")
            }
        }
    }
}