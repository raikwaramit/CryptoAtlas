package com.raikwaramit.cryptoatlas.presentation.coin_list_home.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.raikwaramit.cryptoatlas.R

/**
 * Composable to set the snackbar host and showing it when a coin is added to the favorites.
 */
@Composable
fun FavoriteAddedSnackBar(snackBarHostState: SnackbarHostState, modifier: Modifier = Modifier) {
    SnackbarHost(
        hostState = snackBarHostState,
        snackbar = {
            Card(elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)) {
                val composition by rememberLottieComposition(
                    LottieCompositionSpec.RawRes(
                        R.raw.favorite
                    )
                )
                LottieAnimation(
                    composition = composition,
                    iterations = 3,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(100.dp)
                        .align(CenterHorizontally)
                )
                Text(text = "Coin added to favorite.")
            }
        },
        modifier = modifier
    )
}