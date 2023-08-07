package com.raikwaramit.cryptoatlas.presentation.detail.coin_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.raikwaramit.cryptoatlas.presentation.common_component.LoadingAnimation
import com.raikwaramit.cryptoatlas.presentation.detail.DetailViewModel
import com.raikwaramit.cryptoatlas.presentation.detail.coin_detail.components.CoinTag
import com.raikwaramit.cryptoatlas.presentation.detail.coin_detail.components.TeamListItem

/**
 * Coin details screen for showing the specific coin related details.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailScreen(viewModel: DetailViewModel) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        state.coinDetails?.let { coin ->
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp, top = 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Card(modifier = Modifier.sizeIn(minWidth = 40.dp)) {
                            Text(
                                text = "${coin.rank}",
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier.align(CenterHorizontally)
                            )
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "${coin.name} (${coin.symbol})",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.weight(8f)
                        )
                        Text(
                            text = if (coin.isActive) "active" else "Inactive",
                            color = if (coin.isActive) Color.Green else Color.Red,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )

                    }
                    HorizontalDivider()
                    coin.logo?.let { logoLink ->
                        AsyncImage(
                            model = logoLink,
                            contentDescription = "Currency logo",
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center)
                                .size(100.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = coin.description, style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = "Tags")
                    FlowRow(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        coin.tags.forEach { tag ->
                            CoinTag(tag = tag)
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = "Team members", style = MaterialTheme.typography.headlineMedium)
                }
                items(coin.team) { teamMember ->
                    TeamListItem(
                        teamMember = teamMember,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                }
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            LoadingAnimation(modifier = Modifier.align(Alignment.Center))
        }
    }
}