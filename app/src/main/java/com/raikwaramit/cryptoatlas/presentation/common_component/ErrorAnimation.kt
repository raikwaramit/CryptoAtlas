package com.raikwaramit.cryptoatlas.presentation.common_component

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.raikwaramit.cryptoatlas.R

@Composable
fun ErrorAnimation(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.error
        )
    )
    LottieAnimation(
        composition = composition,
        iterations = Int.MAX_VALUE,
        modifier = modifier.size(64.dp)
    )
}