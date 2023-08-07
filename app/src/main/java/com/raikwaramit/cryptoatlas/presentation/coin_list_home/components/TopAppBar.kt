package com.raikwaramit.cryptoatlas.presentation.coin_list_home.components

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material.icons.twotone.MoreVert
import androidx.compose.material.icons.twotone.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

/**
 * Top app bar composable for showing it in main coin list home screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    searchState: Boolean = false,
    titleVisibility: Boolean = true,
    scrollBehavior: TopAppBarScrollBehavior,
    onSearchStateChanged: () -> Unit,
    onSearch: (String) -> Unit,
    onNavigationIconClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    onAboutMenuClick: () -> Unit,
    onShareMenuClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            if (searchState) {
                var text by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = text,
                    onValueChange = {
                        text = it
                        onSearch(it)
                    },
                    textStyle = MaterialTheme.typography.titleMedium,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {
                    }),
                    singleLine = true,
                    modifier = Modifier.padding(5.dp)
                )
            } else {
                AnimatedVisibility(
                    visible = titleVisibility, enter = slideInVertically(),
                    exit = slideOutVertically()
                ) {

                    Text(
                        text = "Crypto Atlas",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(),
        navigationIcon = {
            IconButton(onClick = { onNavigationIconClick() }) {
                Icon(imageVector = Icons.TwoTone.Menu, contentDescription = "")
            }
        },
        actions = {
            var mDisplayMenu by remember { mutableStateOf(false) }
            IconButton(onClick = onSearchStateChanged) {
                Icon(imageVector = Icons.TwoTone.Search, contentDescription = "Search button")
            }
            AnimatedVisibility(visible = !searchState) {
                Row {
                    IconButton(onClick = onFavoriteClick) {
                        Icon(
                            imageVector = Icons.TwoTone.Favorite,
                            contentDescription = "Favorite button"
                        )
                    }
                    IconButton(onClick = { mDisplayMenu = true }) {
                        Icon(imageVector = Icons.TwoTone.MoreVert, contentDescription = "Menu")
                    }
                    DropdownMenu(
                        expanded = mDisplayMenu,
                        onDismissRequest = { mDisplayMenu = false }
                    ) {
                        val context = LocalContext.current
                        DropdownMenuItem(
                            text = { Text(text = "Share") },
                            onClick = onShareMenuClick
                        )
                        DropdownMenuItem(
                            text = { Text(text = "About app") },
                            onClick = onAboutMenuClick
                        )
                        DropdownMenuItem(text = { Text(text = "Rate us") }, onClick = {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://github.com/raikwaramit/CryptoAtlas")
                            )
                            context.startActivity(intent)
                        })
                    }
                }
            }
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(15.dp)),
    )
    BackHandler(enabled = searchState) {
        onSearchStateChanged()
    }
}