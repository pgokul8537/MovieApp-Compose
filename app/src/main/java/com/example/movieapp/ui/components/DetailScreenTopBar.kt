package com.example.movieapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenTopBar(onBackClick: () -> Unit) {
    val iconModifier = Modifier
        .size(50.dp)
        .padding(start = 16.dp)
        .background(
            color = Color.LightGray.copy(0.5f),
            shape = CircleShape
        )
    TopAppBar(
        title = {
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Transparent.copy(alpha = 0.1f)),
        navigationIcon = {
            IconButton(modifier = iconModifier, onClick = {
                onBackClick.invoke()
            }) {
                Icon(
                    modifier = Modifier.size(22.dp),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "back",
                    tint = Color.Black
                )
            }
        }
    )
}