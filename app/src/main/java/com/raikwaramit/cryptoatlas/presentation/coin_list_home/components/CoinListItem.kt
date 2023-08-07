package com.raikwaramit.cryptoatlas.presentation.coin_list_home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.CheckCircle
import androidx.compose.material.icons.twotone.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.raikwaramit.cryptoatlas.domain.model.Coin

/**
 * Composable for List item for showing the coin list in home coin screen.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoinListItem(
    coin: Coin,
    onItemClick: () -> Unit,
    onLongClick: () -> Unit,
) {
    rememberCoroutineScope()
    OutlinedCard(
        shape = CardDefaults.outlinedShape,
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary),
        modifier = Modifier.padding(start = 3.dp, end = 3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 3.dp, end = 3.dp)
                .combinedClickable(
                    onClick = { onItemClick() },
                    onLongClick = onLongClick
                )
                .padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Text(
                text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis
            )
            Icon(
                imageVector = if (coin.isActive == true) Icons.TwoTone.CheckCircle else Icons.TwoTone.Close,
                contentDescription = ""
            )
        }
    }
}