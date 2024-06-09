package com.vikas.vchat.feature.editProfile.comp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import com.streamliners.pickers.media.PickedMedia
import com.vikas.vchat.ui.comp.general.AsyncImage

@Composable
fun ProfileImage(
    pickedMedia: PickedMedia,
    onClick: () -> Unit,
    modifier: Modifier
) {

    AsyncImage(
        uri = pickedMedia.uri,
        modifier = modifier
            .size(100.dp)
            .clip(CircleShape)
            .clickable { onClick() }
    )
    
}