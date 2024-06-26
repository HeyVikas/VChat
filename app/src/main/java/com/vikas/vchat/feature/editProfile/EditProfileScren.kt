package com.vikas.vchat.feature.editProfile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.streamliners.compose.comp.select.RadioGroup
import com.streamliners.compose.comp.textInput.TextInputLayout
import com.streamliners.compose.comp.textInput.config.InputConfig
import com.streamliners.compose.comp.textInput.config.text
import com.streamliners.compose.comp.textInput.state.TextInputState
import com.streamliners.compose.comp.textInput.state.allHaveValidInputs
import com.streamliners.compose.comp.textInput.state.value
import com.streamliners.pickers.media.FromGalleryType
import com.streamliners.pickers.media.MediaPickerDialog
import com.streamliners.pickers.media.MediaPickerDialogState
import com.streamliners.pickers.media.MediaType
import com.streamliners.pickers.media.PickedMedia
import com.streamliners.pickers.media.rememberMediaPickerDialogState
import com.vikas.vchat.domain.Gender
import com.vikas.vchat.domain.User
import com.vikas.vchat.feature.editProfile.comp.AddImageButton
import com.vikas.vchat.feature.editProfile.comp.ProfileImage
import com.vikas.vchat.ui.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    viewModel: EditProfileViewModel,
    email: String,
    navController: NavHostController,
) {

    val mediaPickerDialogeState = rememberMediaPickerDialogState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Profile") }
            )
        },

        snackbarHost = {
            //TODO : SanckBar not visible keyboard not open

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.fillMaxWidth()
            )
        }

    ) { paddingValues ->

        val image = remember {
            mutableStateOf<PickedMedia?>(null)
        }

        val nameInput = remember {
            mutableStateOf(
                TextInputState(
                    label = "Name",
                    inputConfig = InputConfig.text {
                        minLength = 3
                        maxLength = 30
                    }
                )
            )
        }
        val bioInput = remember {
            mutableStateOf(
                TextInputState(
                    label = "Bio",
                    inputConfig = InputConfig.text {
                        minLength = 1
                        maxLength = 50
                    }
                )
            )
        }


        val gender = remember { mutableStateOf<Gender?>(null) }

        var genderError by remember { mutableStateOf(false) }

        LaunchedEffect(key1 = gender.value) {
            if (gender.value != null) genderError = false

        }

        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            val initImagePicker = {
                mediaPickerDialogeState.value = MediaPickerDialogState.Visible(
                    type = MediaType.Image,
                    allowMultiple = false,
                    fromGalleryType = FromGalleryType.VisualMediaPicker
                ) { getList ->

                    scope.launch {
                        val list = getList()
                        list.firstOrNull()?.let {
                            image.value = it
                        }
                    }
                }
            }

            image.value?.let {
                ProfileImage(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    pickedMedia = it,
                    onClick = initImagePicker
                )
            } ?: run {
                AddImageButton(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = initImagePicker
                )
            }

            TextInputLayout(state = nameInput)

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { },
                enabled = false,
                label = { Text(text = "Email") }
            )

            TextInputLayout(state = bioInput)

            Card {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 12.dp, vertical = 8.dp
                        )
                ) {

                    RadioGroup(
                        title = "Gender",
                        state = gender,
                        options = Gender.entries.toList(),
                        labelExtractor = { it.name }
                    )
                    if (genderError) {
                        Text(text = "Required")
                    }
                }
            }


            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 12.dp),
                onClick = {
                    if (
                        TextInputState.allHaveValidInputs(
                            nameInput, bioInput
                        )
                    ) {
                        gender.value?.let {
                            val user = User(
                                name = nameInput.value(),
                                email = email,
                                bio = bioInput.value(),
                                gender = it
                            )
                            viewModel.saveUser(user) {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Registration Successful")
                                }

                                navController.navigate(Screen.Home.route)
                            }
                        }
                    }
                    if (gender.value == null) {
                        genderError = true
                    }
                }
            ) {
                Text(text = "Save")
            }
        }
    }

        MediaPickerDialog(
            state = mediaPickerDialogeState,
            authority = "com.vikas.vchat.fileprovider"
        )
    }
