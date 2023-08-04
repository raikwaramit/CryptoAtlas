package com.raikwaramit.cryptoatlas.presentation.coin_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.raikwaramit.cryptoatlas.R

@Composable
fun MainWelcomeHeading(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(10.dp)
            .height(300.dp)
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.header_animation))
        LottieAnimation(
            composition = composition,
            iterations = Int.MAX_VALUE,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
        )
        Text(
            text = "Welcome to CryptoAtlas",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .weight(8f)
                .align(CenterHorizontally)
        )
    }
}