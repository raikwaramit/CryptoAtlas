package com.raikwaramit.cryptoatlas.presentation.coin_list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.raikwaramit.cryptoatlas.domain.model.Coin

@Composable
fun CoinListItem(
    coin: Coin,
    onItemClick: (Coin) -> Unit,
) {
    OutlinedCard(
        shape = CardDefaults.outlinedShape,
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary),
        modifier = Modifier.padding(start = 3.dp, end = 3.dp)

    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 3.dp, end = 3.dp)
            .clickable { onItemClick(coin) }
            .padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween)
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