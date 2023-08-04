package com.raikwaramit.cryptoatlas.presentation.coin_list.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material.icons.twotone.MoreVert
import androidx.compose.material.icons.twotone.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    titleVisibility: Boolean = true,
    scrollBehavior: TopAppBarScrollBehavior,
    onNavigationIconClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            AnimatedVisibility(
                visible = titleVisibility, enter = slideInVertically(),
                exit = slideOutVertically()
            ) {
                Text(
                    text = "Welcome to CryptoAtlas",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(),
        navigationIcon = {
            IconButton(onClick = { onNavigationIconClick() }) {
                Icon(imageVector = Icons.TwoTone.Menu, contentDescription = "")
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.TwoTone.Settings, contentDescription = "")
            }
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.TwoTone.MoreVert, contentDescription = "")
            }
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(15.dp)),
    )
}