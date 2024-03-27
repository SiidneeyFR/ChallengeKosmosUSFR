package com.example.challengekosmosusfr.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.challengekosmosusfr.domain.Character

@Composable
fun CharacterItem(
    character: Character
) {
    val isShowDetail = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 16.dp)
    ) {
        Column(
            modifier = Modifier
                .border(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(8.dp),
                    width = 1.dp
                )
                .fillMaxWidth()
                .padding(0.dp)
        ) {
            Row() {
                AsyncImage(
                    model = character.image,
                    contentDescription = character.name,
                    modifier = Modifier
                        .fillMaxWidth(.25f)
                        .clip(
                            RoundedCornerShape(
                                topStart = 8.dp, bottomStart = 8.dp
                            )
                        )
                )
                Column(
                    modifier = Modifier.align(CenterVertically)
                ) {
                    Text(
                        text = character.name,
                        color = MaterialTheme.colorScheme.secondary,
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                    Row(
                        modifier = Modifier.align(CenterHorizontally)
                    ) {
                        ButtonDetail(
                            isShowDetail = isShowDetail.value,
                            onClick = {
                                isShowDetail.value = !isShowDetail.value
                            }
                        )
                    }
                }
            }
            AnimatedVisibility(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .fillMaxWidth(),
                visible = isShowDetail.value
            ) {
                CaracterDetailItem(
                    character = character
                )
            }
        }
    }
}