package com.vikas.vchat.ui.comp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vikas.vchat.R
import com.vikas.vchat.domain.Gender
import com.vikas.vchat.domain.User
import com.vikas.vchat.helper.userInitialBasedProfileImage
import com.vikas.vchat.ui.comp.general.AsyncImage
import com.vikas.vchat.ui.theme.Neutral50

@Composable
fun UserCard(
    user: User
)  {
        Card {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                AsyncImage(
                    uri = user.profileImageUrl ?: userInitialBasedProfileImage(user.name),
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape),
                        placeholder = painterResource(id = R.drawable.ic_person)
                )

                Column (
                    modifier = Modifier.weight(1f)
                ){
                    Text(
                        text = user.name,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black
                    )
                    Text(
                        text = user.email,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Neutral50
                    )
                }
            }
        }
}

@Preview
@Composable
fun UserPreview() {
    UserCard(
        user = User(
            name = "Vikas Lalwani",
            email = "namastelalwani@gmail.com",
            profileImageUrl = "",
            bio = "",
            dob = "",
            gender = Gender.Male
        )
    )
    
}