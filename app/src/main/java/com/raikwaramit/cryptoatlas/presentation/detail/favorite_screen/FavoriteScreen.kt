package com.raikwaramit.cryptoatlas.presentation.detail.favorite_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raikwaramit.cryptoatlas.presentation.detail.DetailViewModel

@Composable
fun FavoriteScreen(viewModel: DetailViewModel, onItemClicked: (String) -> Unit) {
    Box(modifier = Modifier.fillMaxSize())
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        items(viewModel.favoriteState.value) { favoriteCoin ->
            Card(elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.clickable {
                        onItemClicked(favoriteCoin.coinId)
                    }) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(3.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "${favoriteCoin.coinName}",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        IconButton(
                            modifier = Modifier.size(36.dp),
                            onClick = { viewModel.deleteCoinFromDataBase(favoriteCoin) }) {
                            Icon(
                                imageVector = Icons.TwoTone.Delete,
                                contentDescription = "Delete favorite from database."
                            )
                        }
                    }

                    Text(
                        text = "${favoriteCoin.symbol}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.align(CenterHorizontally)
                    )
                }
            }
        }
    }
}